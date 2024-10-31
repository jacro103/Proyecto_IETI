package org.escuelaing.eci.Config;


import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class UsersConfiguration implements CorsConfigurationSource{

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration config = new CorsConfiguration();
        try {
            config.setAllowedOrigins(List.of("http://" +Runtime.getRuntime().exec("hostname")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        return config;
    }
    
}
