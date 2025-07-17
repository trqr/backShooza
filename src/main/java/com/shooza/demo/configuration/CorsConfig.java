package com.shooza.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permet toutes les routes
                .allowedOrigins("http://localhost:5173/") // URL du front
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*") // Autorise tous les en-têtes
                .allowCredentials(true); // Si vous avez besoin de cookies ou d'authentification
    }

}

