package com.studymp.presentation.controllers;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.MailFactory;
import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by qwerty on 20.03.2017.
 */
@Controller
@RequestMapping("/api/auth")
public class AuthorizationController {
    private static final Logger LOGGER = LogManager.getLogger(AuthorizationController.class);

    private final ResponseDtoFactory responseDtoFactory;
    private final UserService userService;
    private final MailFactory mailFactory;

    @Autowired
    public AuthorizationController(ResponseDtoFactory responseDtoFactory, UserService userService, MailFactory mailFactory) {
        this.responseDtoFactory = responseDtoFactory;
        this.userService = userService;
        this.mailFactory = mailFactory;
    }

    @RequestMapping(
            value = "/{username}/change-password",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity changePassword(@PathVariable("username") String encodedUsername) {
        String username;
        try {
            username = URLDecoder.decode(encodedUsername, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Не удалось декодировать %s", encodedUsername)));
        }
        User user;
        try {
            user = userService.findByUsername(username);
            mailFactory.sendResetPasswordEmail(user);
        } catch (NotFoundException e) {
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Не удалось найти пользователя %s в базе", username)));
        } catch (Exception e){
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Ошибка при попытки отправления ссылки на восстановление пароля для %s ", username)));
        }
        return ResponseEntity.ok(responseDtoFactory.success());
    }

}
