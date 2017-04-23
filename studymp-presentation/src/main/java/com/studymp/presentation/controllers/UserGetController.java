package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by qwerty on 29.03.2017.
 */
@Controller
@RequestMapping("/admin")
public class UserGetController {
    private static final Logger LOGGER = Logger.getLogger(UserGetController.class);

    private final UserService userService;

    @Autowired
    public UserGetController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    public String getUsers(Model model){
        try {
            List<User> users = userService.findAll();
            //without us
            List<User> result = users.stream()
                    .filter(line -> !SecurityContextHolder.getContext().getAuthentication().getName().equals(line.getUsername()))
                    .collect(Collectors.toList());
            model.addAttribute("users", result);
        } catch (Exception e){
            LOGGER.error("Не удалось получить список пользователей");
        }
        return "users";
    }
}
