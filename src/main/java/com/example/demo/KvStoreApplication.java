package com.example.demo;

import com.example.demo.controller.KvCliController;
import com.example.demo.service.KVService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KvStoreApplication {

    public static void main(String[] args) {
        KVService kvService = new KVService();
        KvCliController CliController = new KvCliController(kvService);
        CliController.start();
    }

}
