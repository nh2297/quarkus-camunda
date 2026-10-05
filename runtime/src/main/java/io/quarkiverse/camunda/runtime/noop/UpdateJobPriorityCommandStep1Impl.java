package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.UpdateJobPriorityCommandStep1;
import io.camunda.client.api.response.UpdateJobPriorityResponse;

public class UpdateJobPriorityCommandStep1Impl extends AbstractStep<UpdateJobPriorityResponse>
        implements UpdateJobPriorityCommandStep1, UpdateJobPriorityCommandStep1.UpdateJobPriorityCommandStep2 {

    @Override
    public UpdateJobPriorityCommandStep2 priority(int priority) {
        return this;
    }

    @Override
    public UpdateJobPriorityCommandStep2 withJobLeaseToken(String jobLeaseToken) {
        return this;
    }

    @Override
    public UpdateJobPriorityCommandStep2 operationReference(long operationReference) {
        return this;
    }

    @Override
    public UpdateJobPriorityCommandStep1 useRest() {
        return this;
    }

    @Override
    public UpdateJobPriorityCommandStep1 useGrpc() {
        return this;
    }
}
