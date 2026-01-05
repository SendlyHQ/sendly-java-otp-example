package com.example.otp;

import com.sendly.Sendly;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SendlyConfig {
    
    @Value("${sendly.api-key}")
    private String apiKey;
    
    @Bean
    public Sendly sendlyClient() {
        return new Sendly(apiKey);
    }
}
