package com.example.demo.utils;

import com.example.demo.KvStoreApplication;
import com.example.demo.configs.ServerConfig;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

public class WebServerLauncher {
    public static void start(ConfigurableApplicationContext parentContext) {
        new SpringApplicationBuilder(ServerConfig.class)
                .web(WebApplicationType.SERVLET)
                .parent(parentContext)
                .properties(
                        "server.port=8080",
                        "spring.jmx.enabled=false",
                        "spring.application.admin.enabled=false"
                )
                .run();
    }
}

