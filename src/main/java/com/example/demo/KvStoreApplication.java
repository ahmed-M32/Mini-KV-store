package com.example.demo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.configs.CliConfig;
import com.example.demo.controller.KvCliController;


@SpringBootApplication
public class KvStoreApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CliConfig.class)
                .web(WebApplicationType.NONE)
                .run(args);

        KvCliController cli = context.getBean(KvCliController.class);
        cli.start(); 
    }
}

