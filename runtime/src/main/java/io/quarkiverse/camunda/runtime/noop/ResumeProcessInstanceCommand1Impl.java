package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.ResumeProcessInstanceCommandStep1;
import io.camunda.client.api.response.ResumeProcessInstanceResponse;

public class ResumeProcessInstanceCommand1Impl extends AbstractStep<ResumeProcessInstanceResponse>
        implements ResumeProcessInstanceCommandStep1 {

    @Override
    public ResumeProcessInstanceCommandStep1 operationReference(long operationReference) {
        return this;
    }
}
