package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.MailFactory;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.interfaces.Validation;
import com.studymp.domain.services.ConfirmEmailImpl;
import com.studymp.domain.services.ResetPasswordImpl;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.ChangePasswordDto;
import com.studymp.presentation.dto.ConfirmAccDto;
import com.studymp.presentation.dto.EmailDto;
import com.studymp.presentation.interfaces.EmailValidator;
import com.studymp.presentation.interfaces.PasswordValidator;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
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
    private final MailFactory mailFactory;
    private final EmailValidator emailValidator;
    private final UserService userService;
    private final PasswordValidator passwordValidator;
    private final ResetPasswordImpl resetPasswordService;
    private final ConfirmEmailImpl confirmEmailmpl;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthorizationController(ResponseDtoFactory responseDtoFactory, MailFactory mailFactory,
                                   EmailValidator emailValidator,
                                   UserService userService, PasswordValidator passwordValidator,
                                   ResetPasswordImpl resetPasswordService,
                                   ConfirmEmailImpl confirmEmailmpl) {
        this.responseDtoFactory = responseDtoFactory;
        this.mailFactory = mailFactory;
        this.emailValidator = emailValidator;
        this.userService = userService;
        this.passwordValidator = passwordValidator;
        this.resetPasswordService = resetPasswordService;
        this.confirmEmailmpl = confirmEmailmpl;
    }

    @RequestMapping(
            value = "/forgotPassword",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody EmailDto emailDto) {
        Validation validation = emailValidator.validateReset(emailDto.email);
        if (!validation.isValid()) {
            LOGGER.debug(String.format("Письмо с ссылкой на восстановление пароля на email %s не выслано", emailDto.email));
            LOGGER.debug(validation.getErrorMessage());
            return ResponseEntity.ok(responseDtoFactory.failure(validation.getErrorMessage()));
        }
        try {
            User user = userService.findByEmail(emailDto.email);
            mailFactory.sendResetPasswordEmail(user);
            return ResponseEntity.ok(responseDtoFactory.success());
        } catch (Exception e) {
            LOGGER.error(String.format("Ошибка при отправки письма с восстановлением пароля для %s ", emailDto.email));
            LOGGER.debug(e);
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Ошибка при отправки письма с восстановлением пароля для %s ", emailDto.email)));
        }
    }

    @RequestMapping(
            value = "/changePassword",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT)
    public ResponseEntity changePassword(@RequestBody ChangePasswordDto changePasswordDto) {
        Validation validation = passwordValidator.validate(changePasswordDto.password);
        if (!validation.isValid()) {
            LOGGER.error(String.format("Пароль состоит из менее 8 символов"));
            LOGGER.debug(validation.getErrorMessage());
            return ResponseEntity.ok(responseDtoFactory.failure(validation.getErrorMessage()));
        }
        try {
            String username = resetPasswordService.getUsernameForHash(changePasswordDto.hash);
            User user = userService.findByUsername(username);
            user.setPassword(passwordEncoder.encode(changePasswordDto.password));
            userService.update(user);
            return ResponseEntity.ok(responseDtoFactory.success());
        } catch (Exception e) {
            LOGGER.error(String.format("Не удалось обновить пароль, возможно устарел hash"));
            LOGGER.debug(e);
            return ResponseEntity.ok(responseDtoFactory.failure("Не удалось обновить пароль"));
        }
    }

    @RequestMapping(
            value = "/confirmAccount",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.POST)
    public ResponseEntity confirmAccount(@RequestBody ConfirmAccDto confirmAccDto) {
        try {
            String username = confirmEmailmpl.getUsernameForHash(confirmAccDto.hash);
            userService.approve(username);
            return ResponseEntity.ok(responseDtoFactory.success());
        } catch (Exception e) {
            LOGGER.error(String.format("Не удалось обновить пароль, возможно устарел hash"));
            LOGGER.debug(e);
            return ResponseEntity.ok(responseDtoFactory.failure("Не удалось обновить пароль"));
        }
    }
}

