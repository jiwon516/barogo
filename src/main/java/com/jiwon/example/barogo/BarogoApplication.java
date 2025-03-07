package com.jiwon.example.barogo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BarogoApplication {

    public static void main(String[] args) {
        SpringApplication.run(BarogoApplication.class, args);
    }

}
