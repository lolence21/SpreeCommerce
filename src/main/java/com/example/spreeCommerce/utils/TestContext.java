package com.example.spreeCommerce.utils;

import java.util.HashMap;
import java.util.Map;

public class TestContext {
    private final Map<String, Object> data = new HashMap<>();

    public void putAll(Map<String, String> data) {
        this.data.putAll(data);
    }

    public void set(String key, Object value) {
        data.put(key, value);
    }

    public <T> T get(String key, Class<T> clazz) {
        return clazz.cast(data.get(key));
    }
}
