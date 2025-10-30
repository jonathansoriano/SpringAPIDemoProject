package com.jonathansoriano.springapidemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class SpringApiDemoApplication implements CommandLineRunner {

    @Value("${spring.profiles.active:default}")
    private String activeProfile;
    public static void main(String[] args) {
        SpringApplication.run(SpringApiDemoApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/students/**")  // applies to all endpoints under /students
                        .allowedOrigins("http://localhost:5500") // your frontend
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // optional but recommended
                        .allowedHeaders("*") // allow all headers (you can restrict if you want)
                        .allowCredentials(true); // optional: if you send cookies/auth
            }
        };
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Active Profile: " + activeProfile);
    }
}
