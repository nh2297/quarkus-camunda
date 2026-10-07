package io.quarkiverse.camunda.examples.panache;

import java.io.InputStream;
import java.util.Map;

import jakarta.enterprise.context.ApplicationScoped;

import io.camunda.client.api.JsonMapper;
import io.camunda.client.api.command.InternalClientException;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.cfg.DateTimeFeature;

@ApplicationScoped
public class CustomJsonMapper implements JsonMapper {

    private static final TypeReference<Map<String, Object>> MAP_TYPE_REFERENCE = new TypeReference<Map<String, Object>>() {
    };

    private static final TypeReference<Map<String, String>> STRING_MAP_TYPE_REFERENCE = new TypeReference<Map<String, String>>() {
    };

    private final ObjectMapper objectMapper;

    public CustomJsonMapper() {
        this.objectMapper = tools.jackson.databind.json.JsonMapper.builder()
                .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .build();
    }

    @Override
    public <T> T fromJson(final String json, final Class<T> typeClass) {
        try {
            return objectMapper.readValue(json, typeClass);
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format("Failed to deserialize json '%s' to class '%s'", json, typeClass), e);
        }
    }

    @Override
    public Map<String, Object> fromJsonAsMap(final String json) {
        try {
            return objectMapper.readValue(json, MAP_TYPE_REFERENCE);
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format("Failed to deserialize json '%s' to 'Map<String, Object>'", json), e);
        }
    }

    @Override
    public Map<String, String> fromJsonAsStringMap(final String json) {
        try {
            return objectMapper.readValue(json, STRING_MAP_TYPE_REFERENCE);
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format("Failed to deserialize json '%s' to 'Map<String, String>'", json), e);
        }
    }

    @Override
    public String toJson(final Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format("Failed to serialize object '%s' to json", value), e);
        }
    }

    @Override
    public String validateJson(final String propertyName, final String jsonInput) {
        try {
            return objectMapper.readTree(jsonInput).toString();
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format(
                            "Failed to validate json input '%s' for property '%s'", jsonInput, propertyName),
                    e);
        }
    }

    @Override
    public String validateJson(final String propertyName, final InputStream jsonInput) {
        try {
            return objectMapper.readTree(jsonInput).toString();
        } catch (final JacksonException e) {
            throw new InternalClientException(
                    String.format("Failed to validate json input stream for property '%s'", propertyName), e);
        }
    }
}
