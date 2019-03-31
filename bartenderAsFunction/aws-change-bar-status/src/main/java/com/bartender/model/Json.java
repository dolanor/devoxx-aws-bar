package com.bartender.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Json {

    private static final Json DEFAULT_SERIALIZER;
    private final ObjectMapper mapper;

    static {
        ObjectMapper mapper = new ObjectMapper();

        // Don't throw an exception when json has extra fields you are
        // not serializing on. This is useful when you want to use a pojo
        // for deserialization and only care about a portion of the json
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Ignore null values when writing json.
        mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Write times as a String instead of a Long so its human readable.
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        DEFAULT_SERIALIZER = new Json(mapper);
    }

    public static Json serializer() {
        return DEFAULT_SERIALIZER;
    }

    private Json(ObjectMapper mapper) {
        this.mapper = mapper;
    }


    /**
     * @throws JsonException when impossible to deserialize to <code>Class[T]</code>
     */
    public <T> T mapOrError(Map<String, Object> map, Class<T> clazz) {
        try {
            return mapper.readValue(mapper.writeValueAsString(map), clazz);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public Map<String, Object> toMap(String json) {
        try {
            return mapper.readValue(json, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            return new HashMap<>();
        }
    }

    public Optional<String> toJson(Object obj) {
        try {
            return Optional.ofNullable(mapper.writeValueAsString(obj));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    private static class JsonException extends RuntimeException {
        private JsonException(Exception ex) {
            super(ex);
        }
    }
}
