package br.com.personmanagement.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper ModelMapperConfig() {
        return new ModelMapper();
    }
}
