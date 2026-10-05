package io.quarkiverse.camunda.runtime.noop;

import java.util.Map;

import io.camunda.client.api.command.TenantScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.response.CreateClusterVariableResponse;
import io.camunda.client.api.search.enums.ClusterVariableKind;

public class TenantScopedCreateClusterVariable1Impl extends AbstractStep<CreateClusterVariableResponse>
        implements TenantScopedClusterVariableCreationCommandStep1 {

    @Override
    public TenantScopedClusterVariableCreationCommandStep1 create(String name, Object value) {
        return this;
    }

    @Override
    public TenantScopedClusterVariableCreationCommandStep1 metadata(Map<String, Object> metadata) {
        return this;
    }

    @Override
    public TenantScopedClusterVariableCreationCommandStep1 kind(ClusterVariableKind kind) {
        return this;
    }
}
