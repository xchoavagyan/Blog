package com.example.demo;

import com.example.demo.database.CreateDatabaseAndTables;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        CreateDatabaseAndTables.createDatabaseAndTables();
        SpringApplication.run(DemoApplication.class, args);
    }

}
