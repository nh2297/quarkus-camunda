package io.quarkiverse.camunda.runtime.noop;

import io.camunda.client.api.command.AssignProcessInstanceBusinessIdCommandStep1;
import io.camunda.client.api.response.AssignProcessInstanceBusinessIdResponse;

public class AssignProcessInstanceBusinessIdCommand1Impl extends AbstractStep<AssignProcessInstanceBusinessIdResponse>
        implements AssignProcessInstanceBusinessIdCommandStep1,
        AssignProcessInstanceBusinessIdCommandStep1.AssignProcessInstanceBusinessIdCommandStep2 {

    @Override
    public AssignProcessInstanceBusinessIdCommandStep2 businessId(String businessId) {
        return this;
    }

    @Override
    public AssignProcessInstanceBusinessIdCommandStep1 useRest() {
        return this;
    }

    @Override
    public AssignProcessInstanceBusinessIdCommandStep1 useGrpc() {
        return this;
    }
}
