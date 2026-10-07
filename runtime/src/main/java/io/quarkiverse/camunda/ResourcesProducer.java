package io.quarkiverse.camunda;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Reserve;
import jakarta.inject.Singleton;

import io.camunda.client.api.JsonMapper;
import io.camunda.client.impl.CamundaObjectMapper;
import io.quarkus.arc.Unremovable;
import io.smallrye.mutiny.infrastructure.Infrastructure;

@Singleton
public class ResourcesProducer {

    @Produces
    @Singleton
    @Unremovable
    @Reserve
    @Priority(0)
    public JsonMapper defaultJsonMapper() {
        return new CamundaObjectMapper();
    }

    @Produces
    @Singleton
    @Unremovable
    @Reserve
    @Priority(0)
    public ScheduledExecutorService defaultZeebeScheduledExecutorService() {
        return Infrastructure::getDefaultWorkerPool;
    }
}
