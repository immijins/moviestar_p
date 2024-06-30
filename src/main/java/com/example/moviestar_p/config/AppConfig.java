package com.example.moviestar_p.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class AppConfig {
    @Value("${upload.path}")
    private String uploadPath;

    @Bean
    CommandLineRunner init() {
        return args -> {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
        };
    }
}
