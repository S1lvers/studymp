package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.MailFactory;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.interfaces.Validation;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import com.studymp.presentation.interfaces.UserMapping;
import com.studymp.presentation.interfaces.UserValidator;;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qwerty on 14.03.2017.
 */
@Controller
@RequestMapping("api/auth")
public class UserPutController {
    private static final Logger LOGGER = Logger.getLogger(UserPutController.class);

    private final ResponseDtoFactory responseDtoFactory;
    private final UserValidator userValidator;
    private final UserMapping userMapping;
    private final UserService userService;
    private final MailFactory mailFactory;

    @Autowired
    public UserPutController(ResponseDtoFactory responseDtoFactory, UserValidator userValidator,
                             UserMapping userMapping, UserService userService, MailFactory mailFactory) {
        this.responseDtoFactory = responseDtoFactory;
        this.userValidator = userValidator;
        this.userMapping = userMapping;
        this.userService = userService;
        this.mailFactory = mailFactory;
    }

    @RequestMapping(
            value = "/register",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT
    )
    public ResponseEntity putUser(@RequestBody UserDto userDto){
        Validation validation = userValidator.validatePutDto(userDto);
        if (!validation.isValid()){
            LOGGER.info("Пользователь не создан");
            LOGGER.debug(validation.getErrorMessage());
            return ResponseEntity.ok(responseDtoFactory.failure(validation.getErrorMessage()));
        }
        User user = userMapping.map(userDto);
        userService.create(user);
        try {
            mailFactory.sendConfirmEmail(user);
        }
        catch (Exception e){
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Ошибка при попытке отправки ссылки на подтверждение аккаунта")));
        }
        return ResponseEntity.ok(responseDtoFactory.success());
    }
}
