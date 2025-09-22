package com.todo.ToDoApp.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Value("${FRONTEND_URL}")
    private String frontendUrl;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry corsRegistry){
                corsRegistry.addMapping("/**")//apply to all end point
                            .allowedOrigins(
                        "https://trelish.vercel.app",
                        "https://trelish-frontend.vercel.app",
                        "https://trelish-frontend.vercel.app/todos",
                        "http://localhost:3030"
                )
                            .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                            .allowedHeaders("*")
                            .allowCredentials(true);
            }
        };
    }
}
