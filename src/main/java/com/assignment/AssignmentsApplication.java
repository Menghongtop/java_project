package com.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.assignment.Controller", "com.assignment.Config", "com.assignment.Repository", "com.assignment"})
@EnableJpaRepositories(basePackages = {"com.assignment.Repository"})
public class AssignmentsApplication {
    public static void main(String[] args) {
        SpringApplication.run(AssignmentsApplication.class, args);
    }
}