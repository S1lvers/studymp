package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.MailFactory;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.interfaces.Validation;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import com.studymp.presentation.interfaces.UserMapping;
import com.studymp.presentation.interfaces.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by qwerty on 29.03.2017.
 */
@Controller
@RequestMapping("/admin")
public class UserGetController {
    private static final Logger LOGGER = Logger.getLogger(UserPutController.class);

    private final ResponseDtoFactory responseDtoFactory;
    private final UserValidator userValidator;
    private final UserMapping userMapping;
    private final UserService userService;
    private final MailFactory mailFactory;

    @Autowired
    public UserGetController(ResponseDtoFactory responseDtoFactory, UserValidator userValidator,
                             UserMapping userMapping, UserService userService, MailFactory mailFactory) {
        this.responseDtoFactory = responseDtoFactory;
        this.userValidator = userValidator;
        this.userMapping = userMapping;
        this.userService = userService;
        this.mailFactory = mailFactory;
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    public String getUsers(Model model){
        try {
            List<User> users = userService.findAll();
            model.addAttribute("users", users);
        } catch (Exception e){
            LOGGER.error("Не удалось получить список пользователей");
        }
        return "users";
    }
}
