package com.fiap.onescjr.creditcardstudentweb.config;

import com.fiap.onescjr.creditcardstudentweb.mapper.StudentMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public StudentMapper createStudentMapper() {
        return new StudentMapper();
    }

    //TODO: Considerar injetar o "TransactionMapper"

}
