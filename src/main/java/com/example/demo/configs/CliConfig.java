package com.example.demo.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "com.example.demo.service",
        "com.example.demo.controller"
})
public class CliConfig {
}
