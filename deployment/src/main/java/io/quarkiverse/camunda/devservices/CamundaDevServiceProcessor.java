package io.quarkiverse.camunda.devservices;

import static io.quarkiverse.camunda.CamundaProcessor.FEATURE_NAME;
import static io.quarkiverse.camunda.testcontainer.CamundaContainerRuntimePorts.CAMUNDA_REST_API;
import static io.quarkus.devservices.common.ContainerLocator.locateContainerWithLabels;

import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.jboss.logging.Logger;
import org.testcontainers.utility.DockerImageName;

import io.camunda.client.CamundaClient;
import io.quarkiverse.camunda.CamundaDevServiceBuildTimeConfig;
import io.quarkiverse.camunda.testcontainer.CamundaContainer;
import io.quarkiverse.camunda.testcontainer.LogLevel;
import io.quarkus.deployment.IsDevServicesSupportedByLaunchMode;
import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.annotations.BuildSteps;
import io.quarkus.deployment.builditem.DevServicesResultBuildItem;
import io.quarkus.deployment.builditem.DevServicesSharedNetworkBuildItem;
import io.quarkus.deployment.builditem.DockerStatusBuildItem;
import io.quarkus.deployment.builditem.LaunchModeBuildItem;
import io.quarkus.deployment.dev.devservices.DevServicesConfig;
import io.quarkus.devservices.common.ContainerLocator;
import io.quarkus.runtime.LaunchMode;
import io.quarkus.runtime.configuration.ConfigUtils;

@BuildSteps(onlyIf = { IsDevServicesSupportedByLaunchMode.class, DevServicesConfig.Enabled.class })
public class CamundaDevServiceProcessor {

    private static final String DEFAULT_CAMUNDA_CONTAINER_IMAGE = "camunda/camunda";

    private static final String DEFAULT_CAMUNDA_VERSION = CamundaClient.class.getPackage().getImplementationVersion();

    private static final DockerImageName CAMUNDA_IMAGE_NAME = DockerImageName.parse(DEFAULT_CAMUNDA_CONTAINER_IMAGE)
            .withTag(DEFAULT_CAMUNDA_VERSION);

    private static final Logger log = Logger.getLogger(CamundaDevServiceProcessor.class);
    private static final String PROP_CAMUNDA_GATEWAY_ADDRESS = "quarkus.camunda.client.broker.gateway-address";
    private static final String PROP_CAMUNDA_REST_ADDRESS = "quarkus.camunda.client.broker.rest-address";
    private static final String PROP_TEST_GATEWAY_ADDRESS = "quarkiverse.camunda.devservices.test.gateway-address";
    private static final String PROP_TEST_REST_ADDRESS = "quarkiverse.camunda.devservices.test.rest-address";
    private static final String PROP_TEST_MONITORING_ADDRESS = "quarkiverse.camunda.devservices.test.monitoring-address";
    public static final String DEV_SERVICE_LABEL = "quarkus-dev-service-camunda";
    private static final ContainerLocator camundaContainerLocator = locateContainerWithLabels(CAMUNDA_REST_API,
            DEV_SERVICE_LABEL);

    @BuildStep
    public DevServicesResultBuildItem startCamundaContainers(LaunchModeBuildItem launchMode,
            List<DevServicesSharedNetworkBuildItem> devServicesSharedNetworkBuildItem,
            CamundaDevServiceBuildTimeConfig buildTimeConfig,
            DockerStatusBuildItem dockerStatusBuildItem,
            DevServicesConfig devServicesConfig) {

        CamundaDevServicesConfig config = buildTimeConfig.devService();
        if (!devServicesRequired(dockerStatusBuildItem, config)) {
            return null;
        }

        boolean test = launchMode.isTest();
        DevServicesResultBuildItem discovered = discoverRunningService(config, launchMode.getLaunchMode(), test);
        if (discovered != null) {
            return discovered;
        }

        boolean useSharedNetwork = DevServicesSharedNetworkBuildItem.isSharedNetworkRequired(devServicesConfig,
                devServicesSharedNetworkBuildItem);
        Optional<Duration> timeout = devServicesConfig.timeout();
        DockerImageName image = config.imageName().map(DockerImageName::parse).orElse(CAMUNDA_IMAGE_NAME);

        return DevServicesResultBuildItem.owned()
                .feature(FEATURE_NAME)
                .serviceName(config.serviceName())
                .serviceConfig(config)
                .startable(() -> {
                    CamundaContainer container = new CamundaContainer(image, useSharedNetwork,
                            new CamundaDevServiceLogLevel(config.log()))
                            .withSharedServiceLabel(launchMode.getLaunchMode(), config.serviceName());
                    timeout.ifPresent(container::withStartupTimeout);
                    if (config.reuse()) {
                        container.withReuse(true);
                    }
                    return container;
                })
                .configProvider(configProvider(test))
                .postStartHook(container -> log.infof("Camunda is ready to accept connections on %s (gRPC) and %s (REST)",
                        container.getGrpcApiAddress(), container.getRestApiAddress()))
                .build();
    }

    private static boolean devServicesRequired(DockerStatusBuildItem dockerStatusBuildItem, CamundaDevServicesConfig config) {
        if (!config.enabled()) {
            // explicitly disabled
            log.debug("Not starting dev services for Camunda as it has been disabled in the config");
            return false;
        }

        if (ConfigUtils.isPropertyPresent(PROP_CAMUNDA_GATEWAY_ADDRESS)) {
            log.debug("Not starting dev services for Camunda as '" + PROP_CAMUNDA_GATEWAY_ADDRESS + "' have been provided");
            return false;
        }

        if (ConfigUtils.isPropertyPresent(PROP_CAMUNDA_REST_ADDRESS)) {
            log.debug("Not starting dev services for Camunda as '" + PROP_CAMUNDA_REST_ADDRESS + "' have been provided");
            return false;
        }

        if (!dockerStatusBuildItem.isContainerRuntimeAvailable()) {
            log.warn(
                    "Docker isn't working, please configure the Camunda broker servers gateway property ("
                            + PROP_CAMUNDA_GATEWAY_ADDRESS + " OR " + PROP_CAMUNDA_REST_ADDRESS + ").");
            return false;
        }
        return true;
    }

    private static DevServicesResultBuildItem discoverRunningService(CamundaDevServicesConfig config,
            LaunchMode launchMode, boolean test) {
        return camundaContainerLocator.locateContainer(config.serviceName(), config.shared(), launchMode)
                .map(containerAddress -> {
                    URI url = URI.create(containerAddress.getUrl());
                    return DevServicesResultBuildItem.discovered()
                            .feature(FEATURE_NAME)
                            .containerId(containerAddress.getId())
                            .config(configMap(url, url, url, url, url, test))
                            .build();
                })
                .orElse(null);
    }

    /**
     * The application may itself run in a container (shared network) and needs the network-internal address,
     * while the test resource always runs on the host JVM and needs the host-mapped one.
     */
    private static Map<String, Function<CamundaContainer, String>> configProvider(boolean test) {
        Map<String, Function<CamundaContainer, String>> config = new HashMap<>();
        config.put(PROP_CAMUNDA_GATEWAY_ADDRESS, c -> c.getGrpcApiAddress().toString());
        config.put(PROP_CAMUNDA_REST_ADDRESS, c -> c.getRestApiAddress().toString());

        if (test) {
            config.put(PROP_TEST_GATEWAY_ADDRESS, c -> c.getExternalGrpcApiAddress().toString());
            config.put(PROP_TEST_REST_ADDRESS, c -> c.getExternalRestApiAddress().toString());
            config.put(PROP_TEST_MONITORING_ADDRESS, c -> c.getMonitoringApiAddress().toString());
        }
        return config;
    }

    private static Map<String, String> configMap(URI grpcApiUri, URI restApiUri,
            URI externalGrpcApiUri, URI externalRestApiUri, URI externalMonitoringApiUri,
            boolean test) {
        Map<String, String> config = new HashMap<>();
        config.put(PROP_CAMUNDA_GATEWAY_ADDRESS, grpcApiUri.toString());
        config.put(PROP_CAMUNDA_REST_ADDRESS, restApiUri.toString());

        if (test) {
            config.put(PROP_TEST_GATEWAY_ADDRESS, externalGrpcApiUri.toString());
            config.put(PROP_TEST_REST_ADDRESS, externalRestApiUri.toString());
            config.put(PROP_TEST_MONITORING_ADDRESS, externalMonitoringApiUri.toString());
        }
        return config;
    }

    public static final class CamundaDevServiceLogLevel {
        public final LogLevel camundaLogLevel;
        public final LogLevel zeebeLogLevel;
        public final LogLevel camundaDbRdbmsLogLevel;
        public final LogLevel myBatisLogLevel;

        public CamundaDevServiceLogLevel(CamundaDevServicesConfig.CamundaDevServicesLogLevel logging) {
            this.camundaLogLevel = logging.camundaLogLevel();
            this.zeebeLogLevel = logging.zeebeLogLevel();
            this.camundaDbRdbmsLogLevel = logging.camundaDbRdbmsLogLevel();
            this.myBatisLogLevel = logging.myBatisLogLevel();
        }

    }
}
