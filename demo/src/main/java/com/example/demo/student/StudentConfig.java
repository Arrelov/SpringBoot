package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    "Marian",
                    "mariam@mail.ru",
                    LocalDate.of(2000, 01, 01)
            );

            Student mary = new Student(
                    "Mary",
                    "mary@mail.ru",
                    LocalDate.of(2001, 02, 02)
            );

            repository.saveAll(
                    List.of(mariam, mary)
            );
        };
    }
}
