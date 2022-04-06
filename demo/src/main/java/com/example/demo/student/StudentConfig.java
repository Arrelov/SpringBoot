package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            new Student(
                    "Mariam",
                    "mariam@mail.ru",
                    LocalDate.of(2000, 02, 5),
                    21
            );
        };
    }
}
