package com.example.rmmservicesserverapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.example"})
@SpringBootApplication
public class RmmServicesServerAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(RmmServicesServerAppApplication.class, args);
    }

}
