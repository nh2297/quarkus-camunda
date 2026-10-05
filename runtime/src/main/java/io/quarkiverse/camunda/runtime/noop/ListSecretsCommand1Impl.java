package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;

import io.camunda.client.api.command.ListSecretsCommandStep1;
import io.camunda.client.api.response.ListSecretsResponse;

public class ListSecretsCommand1Impl extends AbstractStep<ListSecretsResponse> implements ListSecretsCommandStep1 {

    @Override
    public ListSecretsCommandStep1 requestTimeout(Duration requestTimeout) {
        return this;
    }
}
