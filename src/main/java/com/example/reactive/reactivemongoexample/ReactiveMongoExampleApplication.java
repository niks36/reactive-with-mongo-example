package com.example.reactive.reactivemongoexample;

import java.util.UUID;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.reactive.reactivemongoexample.model.Employee;
import com.example.reactive.reactivemongoexample.repository.EmployeeRepository;

@SpringBootApplication
public class ReactiveMongoExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReactiveMongoExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner employees(EmployeeRepository employeeRepository) {
        return (String... args) -> {
            employeeRepository
                    .deleteAll()
                    .subscribe(null, null, () -> {
                        Stream.of(new Employee(UUID.randomUUID().toString(), "Sam", 20000L),
                                new Employee(UUID.randomUUID().toString(), "Peter", 25000L),
                                new Employee(UUID.randomUUID().toString(), "Chris", 30000L))
                                .forEach(employee -> employeeRepository.save(employee).subscribe(System.out::println));

                    });
        };
    }

}

