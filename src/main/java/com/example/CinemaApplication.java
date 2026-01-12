package com.example; // ΠΡΟΣΟΧΗ: Μόνο αυτό, τίποτα άλλο.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CinemaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaApplication.class, args);
        System.out.println("Το Cinema Management System ξεκίνησε επιτυχώς!");
    }
}