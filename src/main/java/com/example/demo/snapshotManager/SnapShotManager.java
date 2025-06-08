package com.example.demo.snapshotManager;

import com.example.demo.model.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SnapShotManager {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static void saveSnapshot(ConcurrentHashMap<String, Value> store, String filename) {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), store);
            System.out.println("Snapshot saved to: " + filename);
        } catch (IOException e) {
            System.err.println("Failed to save snapshot: " + e.getMessage());
        }
    }
}
