package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.SuspendProcessInstanceCommandStep1;
import io.camunda.client.api.response.SuspendProcessInstanceResponse;

public class SuspendProcessInstanceCommand1Impl extends AbstractStep<SuspendProcessInstanceResponse>
        implements SuspendProcessInstanceCommandStep1 {

    @Override
    public SuspendProcessInstanceCommandStep1 operationReference(long operationReference) {
        return this;
    }
}
