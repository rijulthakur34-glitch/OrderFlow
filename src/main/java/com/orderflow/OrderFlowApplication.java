package com.orderflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderFlowApplication {

    public static void main(String[] args) {
        System.out.println("=== Starting OrderFlow API ===");
        SpringApplication.run(OrderFlowApplication.class, args);
    }
}
