package com.example.demo.model;

import com.example.demo.enums.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Value {
    private Object value;
    private Long expiresAt;
    private ValueType type;

    public Value() {}
    public Long getExpiresAt() {
        return expiresAt;
    }



    public Value(Object value, Long expiresAt, ValueType type) {
        this.value = value;
        this.expiresAt = expiresAt;
        this.type = type;
    }

    public Object getValue() {

        return value;
    }

    public boolean isExpired() {
        return expiresAt != null && expiresAt < System.currentTimeMillis();
    }

    public ValueType getType() {
        return type;
    }

    public <T> T parseValue(Class<T> clazz) {
        try {
            switch (type) {
                case STRING:
                    return clazz.cast(String.valueOf(value));
                case INT:
                    return clazz.cast(Integer.valueOf(String.valueOf(value)));
                case BOOLEAN:
                    return clazz.cast(Boolean.valueOf(String.valueOf(value)));
                case FLOAT:
                    return clazz.cast(Double.valueOf(String.valueOf(value)));
                case LIST:
                    return parseList(clazz);
                default:
                    throw new IllegalArgumentException("Unsupported type: " + type);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse value of type " + type, e);
        }
    }


    @SuppressWarnings("unchecked")
    private <T> T parseList(Class<T> clazz) {
        if (!List.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException("Expected List type but got " + clazz);
        }

        if (value instanceof String) {
            String str = ((String) value).trim();
            if (str.startsWith("[") && str.endsWith("]")) {
                String content = str.substring(1, str.length() - 1);
                List<String> elements = Arrays.stream(content.split(","))
                        .map(String::trim)
                        .collect(Collectors.toList());
                return (T) elements;
            }
            throw new RuntimeException("Invalid list format: " + value);
        } else if (value instanceof List) {
            return (T) value;
        } else {
            throw new RuntimeException("Cannot convert " + value.getClass() + " to List");
        }
    }

}



