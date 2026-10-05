package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.search.filter.ElementInstanceWaitStateFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ElementInstanceWaitStateSearchRequest;
import io.camunda.client.api.search.response.ElementInstanceWaitStateResult;
import io.camunda.client.api.search.sort.ElementInstanceWaitStateSort;

public class ElementInstanceWaitStateSearchRequest1Impl extends AbstractFinalSearchRequestStep<ElementInstanceWaitStateResult>
        implements ElementInstanceWaitStateSearchRequest {

    @Override
    public ElementInstanceWaitStateSearchRequest filter(ElementInstanceWaitStateFilter value) {
        return this;
    }

    @Override
    public ElementInstanceWaitStateSearchRequest filter(Consumer<ElementInstanceWaitStateFilter> fn) {
        return this;
    }

    @Override
    public ElementInstanceWaitStateSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ElementInstanceWaitStateSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ElementInstanceWaitStateSearchRequest sort(ElementInstanceWaitStateSort value) {
        return this;
    }

    @Override
    public ElementInstanceWaitStateSearchRequest sort(Consumer<ElementInstanceWaitStateSort> fn) {
        return this;
    }
}
