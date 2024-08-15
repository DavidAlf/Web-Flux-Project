package com.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.project.service" })
public class SpringBootPruebasService {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPruebasService.class, args);
    }
}