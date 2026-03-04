package com.milk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.milk.entity.User;
import com.milk.repository.UserRepository;

@SpringBootApplication
public class MilkBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MilkBackendApplication.class, args);
    }


}
