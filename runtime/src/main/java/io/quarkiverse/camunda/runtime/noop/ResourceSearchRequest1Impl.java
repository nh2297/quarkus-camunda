package io.quarkiverse.camunda.runtime.noop;

import java.util.function.Consumer;

import io.camunda.client.api.response.Resource;
import io.camunda.client.api.search.filter.ResourceFilter;
import io.camunda.client.api.search.page.AnyPage;
import io.camunda.client.api.search.request.ResourceSearchRequest;
import io.camunda.client.api.search.sort.ResourceSort;

public class ResourceSearchRequest1Impl extends AbstractFinalSearchRequestStep<Resource>
        implements ResourceSearchRequest {

    @Override
    public ResourceSearchRequest filter(ResourceFilter value) {
        return this;
    }

    @Override
    public ResourceSearchRequest filter(Consumer<ResourceFilter> fn) {
        return this;
    }

    @Override
    public ResourceSearchRequest page(AnyPage value) {
        return this;
    }

    @Override
    public ResourceSearchRequest page(Consumer<AnyPage> fn) {
        return this;
    }

    @Override
    public ResourceSearchRequest sort(ResourceSort value) {
        return this;
    }

    @Override
    public ResourceSearchRequest sort(Consumer<ResourceSort> fn) {
        return this;
    }
}
