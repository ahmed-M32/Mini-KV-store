package com.example.demo;

import com.example.demo.configs.CliConfig;
import com.example.demo.controller.KvCliController;
import com.example.demo.service.KVService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;


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

