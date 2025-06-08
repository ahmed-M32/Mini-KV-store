package com.example.demo.store;

import com.example.demo.model.Value;
import com.example.demo.snapshotManager.SnapShotManager;

import java.util.concurrent.ConcurrentHashMap;

public class KVstore {
    private final ConcurrentHashMap<String, Value> store = new ConcurrentHashMap<>();


    public void set(String k, Value v){
        store.put(k,v);
    }

    public Value get(String k){
        return store.get(k);
    }
    public void delete (String k){
        store.remove(k);
    }
    public Boolean exists(String k){
        return store.containsKey(k);
    }
    public void saveSnapshot(String path){
        SnapShotManager s = null;
        SnapShotManager.saveSnapshot(store,path);
    }

}
