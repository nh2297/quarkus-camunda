package io.quarkiverse.camunda.runtime.noop;

import java.time.Duration;
import java.util.function.Consumer;

import io.camunda.client.api.command.CreateBatchOperationCommandStep1;
import io.camunda.client.api.response.CreateBatchOperationResponse;
import io.camunda.client.api.search.filter.DecisionInstanceFilter;
import io.camunda.client.api.search.filter.JobFilter;
import io.camunda.client.api.search.filter.ProcessInstanceFilter;
import io.camunda.client.api.search.request.TypedFilterableRequest;

public class CreateBatchOperationCommandStep1Impl1<E extends TypedFilterableRequest.SearchRequestFilter>
        extends AbstractStep<CreateBatchOperationResponse>
        implements CreateBatchOperationCommandStep1, CreateBatchOperationCommandStep1.CreateBatchOperationCommandStep2<E>,
        CreateBatchOperationCommandStep1.CreateBatchOperationCommandStep3<E>,
        CreateBatchOperationCommandStep1.JobUpdateStep<E> {

    @SuppressWarnings("unchecked")
    private <T> T self() {
        return (T) this;
    }

    @Override
    public CreateBatchOperationCommandStep2<ProcessInstanceFilter> processInstanceCancel() {
        return self();
    }

    @Override
    public CreateBatchOperationCommandStep2<ProcessInstanceFilter> deleteProcessInstance() {
        return self();
    }

    @Override
    public CreateBatchOperationCommandStep2<ProcessInstanceFilter> resolveIncident() {
        return self();
    }

    @Override
    public ProcessInstanceMigrationStep<ProcessInstanceFilter> migrateProcessInstance() {
        return self();
    }

    @Override
    public ProcessInstanceModificationStep<ProcessInstanceFilter> modifyProcessInstance() {
        return self();
    }

    @Override
    public CreateBatchOperationCommandStep2<DecisionInstanceFilter> deleteDecisionInstance() {
        return self();
    }

    @Override
    public CreateBatchOperationCommandStep3<E> filter(E filter) {
        return this;
    }

    @Override
    public CreateBatchOperationCommandStep3<E> filter(Consumer<E> filter) {
        return this;
    }

    @Override
    public CreateBatchOperationCommandStep2<ProcessInstanceFilter> processInstanceSuspend() {
        return self();
    }

    @Override
    public CreateBatchOperationCommandStep2<ProcessInstanceFilter> processInstanceResume() {
        return self();
    }

    @Override
    public JobUpdateStep<JobFilter> updateJob() {
        return self();
    }

    @Override
    public JobUpdateStep<E> priority(int priority) {
        return this;
    }

    @Override
    public JobUpdateStep<E> retries(int retries) {
        return this;
    }

    @Override
    public JobUpdateStep<E> timeout(long timeout) {
        return this;
    }

    @Override
    public JobUpdateStep<E> timeout(Duration timeout) {
        return this;
    }
}
