package main.java.com.api.edusys.config;

import java.beans.BeanProperty;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
