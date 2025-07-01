package com.example.demo.configs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.example.demo.controller" }) // <== ONLY controllers
@EnableAutoConfiguration
public class ServerConfig {
}
