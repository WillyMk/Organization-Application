package com.example.departmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = {"com.example.departmentservice", "com.example.departmentservice.config"})
public class DepartmentServiceApplication {
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
    public static void main(String[] args) {
        SpringApplication.run(DepartmentServiceApplication.class, args);
    }

}
