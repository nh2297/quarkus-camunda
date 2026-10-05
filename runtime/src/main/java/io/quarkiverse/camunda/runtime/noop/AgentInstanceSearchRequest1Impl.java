package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.AgentInstanceFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.AgentInstanceSearchRequest;
import io.camunda.client.api.search.response.AgentInstance;
import io.camunda.client.api.search.sort.AgentInstanceSort;

public class AgentInstanceSearchRequest1Impl extends AbstractFinalSearchRequestStep<AgentInstance>
        implements AgentInstanceSearchRequest {

    @Override
    public AgentInstanceSearchRequest filter(AgentInstanceFilter value) {
        return this;
    }

    @Override
    public AgentInstanceSearchRequest filter(Consumer<AgentInstanceFilter> fn) {
        return this;
    }

    @Override
    public AgentInstanceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public AgentInstanceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public AgentInstanceSearchRequest sort(AgentInstanceSort value) {
        return this;
    }

    @Override
    public AgentInstanceSearchRequest sort(Consumer<AgentInstanceSort> fn) {
        return this;
    }
}
