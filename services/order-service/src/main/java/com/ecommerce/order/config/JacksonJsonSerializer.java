package com.ecommerce.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;

/**
 * Custom JSON Serializer using Jackson.
 * Replaces the deprecated org.springframework.kafka.support.serializer.JsonSerializer
 * and adds type headers for proper deserialization.
 */
public class JacksonJsonSerializer<T> implements Serializer<T> {

    public static final String TYPE_ID_HEADER = "__TypeId__";
    private final ObjectMapper objectMapper;

    public JacksonJsonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public JacksonJsonSerializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public byte[] serialize(String topic, T data) {
        return serialize(topic, null, data);
    }

    @Override
    public byte[] serialize(String topic, Headers headers, T data) {
        if (data == null) {
            return null;
        }
        try {
            // Add type header for consumer to recognize the type
            if (headers != null) {
                String typeId = getTypeId(data);
                headers.add(TYPE_ID_HEADER, typeId.getBytes(StandardCharsets.UTF_8));
            }
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    /**
     * Maps the class to a type ID that the consumer can use for deserialization.
     * This mapping should match the consumer's spring.json.type.mapping configuration.
     */
    private String getTypeId(T data) {
        String className = data.getClass().getSimpleName();
        // Map to the type IDs expected by the consumer
        return switch (className) {
            case "OrderConfirmationEvent" -> "orderConfirmation";
            default -> data.getClass().getName();
        };
    }
}
