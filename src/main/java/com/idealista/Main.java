package com.idealista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.idealista.infrastructure")
public class Main {
    
    public static void main(String [] args){
        SpringApplication.run(Main.class, args);

    }

}