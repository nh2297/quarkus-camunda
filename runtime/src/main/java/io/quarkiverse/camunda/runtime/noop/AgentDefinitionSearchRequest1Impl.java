package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.AgentDefinitionFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.AgentDefinitionSearchRequest;
import io.camunda.client.api.search.response.AgentDefinition;
import io.camunda.client.api.search.sort.AgentDefinitionSort;

public class AgentDefinitionSearchRequest1Impl extends AbstractFinalSearchRequestStep<AgentDefinition>
        implements AgentDefinitionSearchRequest {

    @Override
    public AgentDefinitionSearchRequest filter(AgentDefinitionFilter value) {
        return this;
    }

    @Override
    public AgentDefinitionSearchRequest filter(Consumer<AgentDefinitionFilter> fn) {
        return this;
    }

    @Override
    public AgentDefinitionSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public AgentDefinitionSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public AgentDefinitionSearchRequest sort(AgentDefinitionSort value) {
        return this;
    }

    @Override
    public AgentDefinitionSearchRequest sort(Consumer<AgentDefinitionSort> fn) {
        return this;
    }
}
