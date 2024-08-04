package com.pedromarininave.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deserialize(String json, Type type) {
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructType(type));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static <T> List<T> deserializeList(String json, Class<T> objectClass) {
        try {
            CollectionType collectionType = objectMapper.getTypeFactory()
                                                        .constructCollectionType(List.class, objectClass);
            return objectMapper.readValue(json, collectionType);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}