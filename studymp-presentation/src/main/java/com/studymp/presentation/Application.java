package com.studymp.presentation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import java.io.IOException;


/**
 * Created by qwerty on 13.03.2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.studymp")
@EntityScan(basePackages = "com.studymp")
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(Application.class, args);
    }
}
