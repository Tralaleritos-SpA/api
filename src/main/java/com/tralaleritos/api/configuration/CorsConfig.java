package com.tralaleritos.api.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // 1. Apply CORS to all paths under your API base
                .allowedOrigins("http://localhost:5173") // 2. Allow requests ONLY from your React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 3. Allow necessary HTTP methods
                .allowedHeaders("*") // 4. Allow all headers
                .allowCredentials(true); // 5. Important if you use cookies or session IDs
    }
}
