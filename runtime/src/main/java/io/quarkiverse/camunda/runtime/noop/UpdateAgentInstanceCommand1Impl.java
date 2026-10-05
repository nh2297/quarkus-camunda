package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.command.AgentInstanceHistoryItem;
import io.camunda.client.api.command.AgentInstanceUpdateStatus;
import io.camunda.client.api.command.UpdateAgentInstanceCommandStep1;
import io.camunda.client.api.response.UpdateAgentInstanceResponse;

public class UpdateAgentInstanceCommand1Impl extends AbstractStep<UpdateAgentInstanceResponse>
        implements UpdateAgentInstanceCommandStep1, UpdateAgentInstanceCommandStep1.UpdateAgentInstanceCommandStep2,
        UpdateAgentInstanceCommandStep1.UpdateAgentInstanceCommandStep3,
        UpdateAgentInstanceCommandStep1.UpdateAgentInstanceCommandStep4 {

    @Override
    public UpdateAgentInstanceCommandStep2 elementInstanceKey(long elementInstanceKey) {
        return this;
    }

    @Override
    public UpdateAgentInstanceCommandStep2 status(AgentInstanceUpdateStatus status) {
        return this;
    }

    @Override
    public UpdateAgentInstanceCommandStep3 jobKey(long jobKey) {
        return this;
    }

    @Override
    public UpdateAgentInstanceCommandStep4 jobLeaseToken(String jobLeaseToken) {
        return this;
    }

    @Override
    public UpdateAgentInstanceCommandStep4 history(List<AgentInstanceHistoryItem> history) {
        return this;
    }
}
