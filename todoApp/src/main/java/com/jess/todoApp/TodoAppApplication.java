package com.jess.todoApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@EnableJpaRepositories("com.jess.todoApp.repositories")
@EntityScan("com.jess.todoApp.model")
@SpringBootApplication
public class TodoAppApplication {

    public static void main(String[] args) {    
        SpringApplication.run(TodoAppApplication.class, args);
    }

}
