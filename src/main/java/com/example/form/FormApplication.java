package com.example.form;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FormApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer configure() {
    	return new WebMvcConfigurer() {
    		@Override
    		public void addCorsMappings(CorsRegistry corsRegistry) {
    			corsRegistry.addMapping("/*").allowedOrigins("*");
    			corsRegistry.addMapping("/*").allowedHeaders("*")
    			.allowedMethods("HEAD", "OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE");
    		}
		};
    }
}
