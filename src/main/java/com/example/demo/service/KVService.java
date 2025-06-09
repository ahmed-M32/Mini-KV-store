package com.example.demo.service;

import com.example.demo.model.Value;
import com.example.demo.store.KVstore;

import java.nio.file.Path;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class KVService {
    private KVstore store;

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
