package com.bearAndPupperCo.sangenWrestlingApp.Configurations;

import org.jdbi.v3.core.Jdbi;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class AppConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/teiai-api/**").allowedOrigins("http://localhost:3000");
            }
        };
    }
    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        return Jdbi.create(dataSource);
    }

}
