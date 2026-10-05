package io.quarkiverse.camunda.runtime.noop;

import java.util.Map;

import io.camunda.client.api.command.GloballyScopedClusterVariableCreationCommandStep1;
import io.camunda.client.api.response.CreateClusterVariableResponse;
import io.camunda.client.api.search.enums.ClusterVariableKind;

public class GloballyScopedCreateClusterVariable1Impl extends AbstractStep<CreateClusterVariableResponse>
        implements GloballyScopedClusterVariableCreationCommandStep1 {

    @Override
    public GloballyScopedClusterVariableCreationCommandStep1 create(String name, Object value) {
        return this;
    }

    @Override
    public GloballyScopedClusterVariableCreationCommandStep1 metadata(Map<String, Object> metadata) {
        return this;
    }

    @Override
    public GloballyScopedClusterVariableCreationCommandStep1 kind(ClusterVariableKind kind) {
        return this;
    }
}
