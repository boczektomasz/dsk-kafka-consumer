package com.mycompany.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsumerDemo {

    // Default port is 8080
    public static void main(String[] args) {
        SpringApplication.run(ConsumerDemo.class, args);
    }
}
