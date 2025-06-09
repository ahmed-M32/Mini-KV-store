package com.example.demo.controller;

import com.example.demo.model.Value;
import com.example.demo.service.KVService;
import com.example.demo.enums.*;
import java.util.List;
import java.util.Scanner;

public class KvCliController {
    private final KVService service;
    private final Scanner scanner;
    private boolean on = true;

    public KvCliController(KVService storeService) {
        this.service = storeService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        printBanner();

        while (on) {
            System.out.print("➜  kv-store » "); // Stylish prompt

            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] tokens = line.split(" ");
            String command = tokens[0].toLowerCase();

            switch (command) {
                case "set" -> handleSet(tokens);
                case "get" -> handleGet(tokens);
                case "del" -> handleDelete(tokens);
                case "exist" -> handleExist(tokens);
                case "save" -> handleSave(tokens);
                case "help"-> handleHelp();

                case "end" -> shutdown();
                default -> System.out.println("❌ Unknown command. Try: set, get, del, exist, save, end");
            }
        }
    }

    private void handleHelp() {
        System.out.println("""
    ╭─────────────────────── HELP ───────────────────────╮
    │  set <key> <value> <type> [ttl_seconds]            │
    │     → Store a key-value pair with optional expiry  │
    │     → Example: set username Ahmed STRING 60        │
    │                                                    │
    │  get <key>                                         │
    │     → Retrieve a value by key                      │
    │     → Example: get username                        │
    │                                                    │
    │  del <key>                                         │
    │     → Delete a key from the store                  │
    │     → Example: del username                        │
    │                                                    │
    │  exist <key>                                       │
    │     → Check if a key exists in the store           │
    │     → Example: exist username                      │
    │                                                    │
    │  save <filename.json>                              │
    │     → Save the entire store as a JSON file         │
    │     → Example: save snapshot.json                  │
    │                                                    │
    │  help                                              │
    │     → Print this very help menu                    │
    │                                                    │
    │  end                                               │
    │     → Exit the CLI                                 │
    ╰────────────────────────────────────────────────────╯
    """);
    }
    private void handleSet(String[] tokens) {
        if (tokens.length < 4) {
            System.out.println("⚠️  Usage: set <key> <value> <type> [ttl_seconds]");
            return;
        }

        String key = tokens[1];
        String rawValue = tokens[2];
        String typeInput = tokens[3].toUpperCase();

        try {
            ValueType type = ValueType.valueOf(typeInput);
            Object parsedValue;

            long ttlSeconds = tokens.length > 4 ? Long.parseLong(tokens[4]) : 0;
            Long expiresAt = ttlSeconds > 0 ? System.currentTimeMillis() + (ttlSeconds * 1000) : null;

            parsedValue = switch (type) {
                case STRING -> rawValue;
                case INT -> Integer.parseInt(rawValue);
                case FLOAT -> Double.parseDouble(rawValue);
                case BOOLEAN -> Boolean.parseBoolean(rawValue);
                case LIST -> List.of(rawValue.split(","));
            };

            Value newVal = new Value(parsedValue, expiresAt, type);
            service.addValue(key, newVal);
            System.out.println(" Value stored successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid value type: " + typeInput);
        } catch (Exception e) {
            System.out.println("Failed to parse value: " + e.getMessage());
        }
    }

    private void handleGet(String[] tokens) {
        if (tokens.length < 2) {
            return;
        }

        Object val = service.getValue(tokens[1]);
        if (val == null) {
            System.out.println("Value not found or expired.");
        } else {
            System.out.println( val);
        }
    }

    private void handleDelete(String[] tokens) {
        if (tokens.length < 2) {
            return;
        }

        service.deleteValue(tokens[1]);
        System.out.println(" Key deleted (if it existed).");
    }

    private void handleExist(String[] tokens) {
        if (tokens.length < 2) {
            return;
        }

        boolean exists = service.valueExists(tokens[1]);
        System.out.println(exists ? " Item exists!" : "Item does not exist in store.");
    }

    private void handleSave(String[] tokens) {
        if (tokens.length < 2) {
            return;
        }

        service.saveSnapShot(tokens[1]);
        System.out.println(" Snapshot saved to: " + tokens[1]);
    }

    private void shutdown() {
        on = false;
        service.stop();
    }

    private void printBanner() {
        System.out.println("                    ╔════════════════════════════════════════════╗");
        System.out.println("                    ║      Welcome to Mini-KV Store CLI          ║");
        System.out.println("                    ╚════════════════════════════════════════════╝");
        System.out.println("Type `help` for usage. Let's store some stuff!");
    }
}
