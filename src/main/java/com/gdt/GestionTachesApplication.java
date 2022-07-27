package com.gdt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class GestionTachesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionTachesApplication.class, args);
    }

}
