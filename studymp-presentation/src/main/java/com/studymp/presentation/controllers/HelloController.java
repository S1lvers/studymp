package com.studymp.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by qwerty on 13.03.2017.
 */

@Controller
public class HelloController {
    @RequestMapping(
            value = {"/index", "/"}
    )
    public String index() {
        System.out.println("#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        return "Greetings from Spring Boot!";
    }
}
