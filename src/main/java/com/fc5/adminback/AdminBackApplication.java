package com.fc5.adminback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AdminBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminBackApplication.class, args);
    }

}
