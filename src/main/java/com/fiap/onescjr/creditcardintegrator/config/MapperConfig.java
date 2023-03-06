package com.fiap.onescjr.creditcardintegrator.config;

import com.fiap.onescjr.creditcardintegrator.mapper.StudentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public StudentMapper createStudentMapper() {
        return new StudentMapper();
    }

}
