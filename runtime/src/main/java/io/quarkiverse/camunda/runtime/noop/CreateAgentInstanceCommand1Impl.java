package io.quarkiverse.camunda.runtime.noop;

import java.util.List;

import io.camunda.client.api.command.AgentInstanceHistoryItem;
import io.camunda.client.api.command.CreateAgentInstanceCommandStep1;
import io.camunda.client.api.response.CreateAgentInstanceResponse;

public class CreateAgentInstanceCommand1Impl extends AbstractStep<CreateAgentInstanceResponse>
        implements CreateAgentInstanceCommandStep1, CreateAgentInstanceCommandStep1.CreateAgentInstanceCommandStep2,
        CreateAgentInstanceCommandStep1.CreateAgentInstanceCommandStep3,
        CreateAgentInstanceCommandStep1.CreateAgentInstanceCommandStep4,
        CreateAgentInstanceCommandStep1.CreateAgentInstanceCommandStep5 {

    @Override
    public CreateAgentInstanceCommandStep2 elementInstanceKey(long elementInstanceKey) {
        return this;
    }

    @Override
    public CreateAgentInstanceCommandStep3 jobKey(long jobKey) {
        return this;
    }

    @Override
    public CreateAgentInstanceCommandStep4 jobLeaseToken(String jobLeaseToken) {
        return this;
    }

    @Override
    public CreateAgentInstanceCommandStep5 history(List<AgentInstanceHistoryItem> history) {
        return this;
    }
}
