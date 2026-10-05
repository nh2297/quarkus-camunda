package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;
import java.util.List;

import io.camunda.client.api.command.ResolveSecretsCommandStep1;
import io.camunda.client.api.response.ResolveSecretsResponse;

public class ResolveSecretsCommand1Impl extends AbstractStep<ResolveSecretsResponse> implements ResolveSecretsCommandStep1 {

    @Override
    public ResolveSecretsCommandStep1 reference(String reference) {
        return this;
    }

    @Override
    public ResolveSecretsCommandStep1 references(List<String> references) {
        return this;
    }

    @Override
    public ResolveSecretsCommandStep1 references(String... references) {
        return this;
    }

    @Override
    public ResolveSecretsCommandStep1 requestTimeout(Duration requestTimeout) {
        return this;
    }
}
