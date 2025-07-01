package com.example.demo.controller;


import com.example.demo.model.Value;
import com.example.demo.service.KVService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class KvHttpController {
    private final KVService service;


    public KvHttpController(KVService Service) {
        this.service = Service;
        System.out.println("KVService hash: " + System.identityHashCode(service));

    }

    @GetMapping("/{key}")
    private ResponseEntity<?> getValue(@PathVariable String key) {
        Object item = service.getValue(key);
        System.out.println(item);
        return ResponseEntity.ok(item);
    }

    @PostMapping("/set/{key}")
    private ResponseEntity<?> setValue(@RequestBody Value value, @PathVariable String key) {


        service.addValue(key, value);
        return ResponseEntity.ok("Item stored");
    }
}
