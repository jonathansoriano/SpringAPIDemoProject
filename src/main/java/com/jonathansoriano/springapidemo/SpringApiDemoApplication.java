package com.jonathansoriano.springapidemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringApiDemoApplication implements CommandLineRunner {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;
    public static void main(String[] args) {
        SpringApplication.run(SpringApiDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Active Profile: " + activeProfile);
    }
}
