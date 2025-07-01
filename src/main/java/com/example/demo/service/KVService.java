package com.example.demo.service;

import com.example.demo.model.Value;
import com.example.demo.store.KVstore;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class KVService {
    private final KVstore store;

    public KVService() {
        store = new KVstore();
    }


    public void addValue(String key, Value value) {
        store.set(key, value);
        System.out.println("Item added");
    }

    public Object getValue(String key) {
        return store.get(key);
    }

    public boolean valueExists(String key) {
        return store.exists(key);
    }

    public void deleteValue(String key) {
        store.delete(key);
    }
    public void saveSnapShot(String path) {
        store.saveSnapshot(path);
    }
    public void stop(){
        store.stop();
    }
}
