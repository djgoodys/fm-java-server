package com.example.fmjavaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.example.fmjavaserver")
@SpringBootApplication(scanBasePackages = "com.example.fmjavaserver")
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    //CODE BELOW ONLY USED ONCE TO CREATE THE DATA IN THE POSTGRESSQL DATABASE FROMTHE THE data.sql file
    // @Bean
    // CommandLineRunner init(DataTransfer dataTransfer) {
    //     return args -> {
    //         // Use absolute path or correct relative path
    //         String sqlFilePath = "C:/JavaProjects/fm-java-server/src/main/java/com/example/fmjavaserver/filtertypesdata.sql"; // Adjust the path as needed
    //         dataTransfer.transferData(sqlFilePath);
    //     };
    // }
}
