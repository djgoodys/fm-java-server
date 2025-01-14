package com.example.fmjavaserver.equipment;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class EquipmentConfig {
    @Bean
CommandLineRunner commandLineRunner(
    EquipmentRepository repository){
    return args ->{
        System.out.println("Hello world");
    };
}
}

