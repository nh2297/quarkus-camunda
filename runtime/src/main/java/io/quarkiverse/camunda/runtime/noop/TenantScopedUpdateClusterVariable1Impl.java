package io.quarkiverse.camunda.runtime.noop;

import java.util.Map;

import io.camunda.client.api.command.TenantScopedClusterVariableUpdateCommandStep1;
import io.camunda.client.api.response.UpdateClusterVariableResponse;

public class TenantScopedUpdateClusterVariable1Impl extends AbstractStep<UpdateClusterVariableResponse>
        implements TenantScopedClusterVariableUpdateCommandStep1 {

    @Override
    public TenantScopedClusterVariableUpdateCommandStep1 update(String name, Object value) {
        return this;
    }

    @Override
    public TenantScopedClusterVariableUpdateCommandStep1 metadata(Map<String, Object> metadata) {
        return this;
    }
}
