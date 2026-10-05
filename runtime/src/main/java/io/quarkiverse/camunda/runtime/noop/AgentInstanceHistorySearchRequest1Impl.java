package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.AgentInstanceHistoryFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.AgentInstanceHistorySearchRequest;
import io.camunda.client.api.search.response.AgentInstanceHistory;
import io.camunda.client.api.search.sort.AgentInstanceHistorySort;

public class AgentInstanceHistorySearchRequest1Impl extends AbstractFinalSearchRequestStep<AgentInstanceHistory>
        implements AgentInstanceHistorySearchRequest {

    @Override
    public AgentInstanceHistorySearchRequest filter(AgentInstanceHistoryFilter value) {
        return this;
    }

    @Override
    public AgentInstanceHistorySearchRequest filter(Consumer<AgentInstanceHistoryFilter> fn) {
        return this;
    }

    @Override
    public AgentInstanceHistorySearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public AgentInstanceHistorySearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public AgentInstanceHistorySearchRequest sort(AgentInstanceHistorySort value) {
        return this;
    }

    @Override
    public AgentInstanceHistorySearchRequest sort(Consumer<AgentInstanceHistorySort> fn) {
        return this;
    }
}
