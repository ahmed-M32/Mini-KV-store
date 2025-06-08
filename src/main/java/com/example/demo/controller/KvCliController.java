package com.example.demo.controller;

import com.example.demo.service.KVService;

import java.util.Scanner;

public class KvCliController {
    private KVService service = new KVService();
    private final Scanner scanner ;

    public KvCliController(KVService storeService) {
        this.service = storeService;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("welcome to Mini-KV store CLI \n");
        System.out.println(">");


    }

}
