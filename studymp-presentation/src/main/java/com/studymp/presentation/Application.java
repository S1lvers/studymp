package com.studymp.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by qwerty on 13.03.2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.studymp")
@EntityScan(basePackages = "com.studymp")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
