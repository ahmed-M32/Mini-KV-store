package com.example.demo.store;

import com.example.demo.model.Value;
import com.example.demo.snapshotManager.SnapShotManager;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class KVstore {
    private final ConcurrentHashMap<String, Value> store = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public KVstore() {
        startCleanupTask();

    }

    private void startCleanupTask() {
        scheduler.scheduleAtFixedRate(this::cleanupExpired, 0, 1, TimeUnit.MINUTES);
    }

    public void cleanupExpired() {
        store.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    public void set(String k, Value v) {
        store.put(k, v);
    }

    public Object get(String key) {
        Value value = store.get(key);
        if (value == null || value.isExpired()) {
            return null;
        }
        return value.getValue();
    }


    public void delete(String k) {
        store.remove(k);
    }

    public Boolean exists(String k) {
        return store.containsKey(k);
    }

    public void saveSnapshot(String path) {
        SnapShotManager s = null;
        SnapShotManager.saveSnapshot(store, path);
    }
    public void stop() {
        scheduler.shutdown();
    }


}
