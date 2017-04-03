package com.studymp.presentation.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qwerty on 03.04.2017.
 */
@Controller
public class LayoutController {

    @RequestMapping(
            value = "/registration.html",
            method = RequestMethod.GET
    )
    public String getRegistrationPage(Model model){
        return "registration";
    }


    @RequestMapping(
            value = "/recoverypass.html",
            method = RequestMethod.GET
    )
    public String getRecoveryPassPage(Model model){
        return "recoverypass";
    }


    @RequestMapping(
            value = "/login.html",
            method = RequestMethod.GET
    )
    public String getLoginPage(Model model){
        return "login";
    }
}
