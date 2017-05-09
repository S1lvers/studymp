package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qwerty on 31.03.2017.
 */
@Controller
@RequestMapping("admin/user/")
public class UserUpdateController {

    private static final Logger LOGGER = Logger.getLogger(UserUpdateController.class);

    private final ResponseDtoFactory responseDtoFactory;
    private final UserService userService;

    @Autowired
    public UserUpdateController(ResponseDtoFactory responseDtoFactory, UserService userService) {
        this.responseDtoFactory = responseDtoFactory;
        this.userService = userService;
    }

    @RequestMapping(
            value = "update",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            method = RequestMethod.PUT
    )
    public ResponseEntity updateUser(@RequestBody UserDto userDto){
        if(SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch((x -> x.getAuthority().equals("ADMIN")))){
            try {
                User user = userService.findByUsername(userDto.username);
                user.setEnabled(userDto.enabled);
                userService.update(user);
                return ResponseEntity.ok(responseDtoFactory.success(user.isEnabled()));
            } catch (Exception e){
                LOGGER.error(String.format("Не удалось обновить пользоватля %s", userDto.username));
                LOGGER.debug(e);
                return ResponseEntity.ok(responseDtoFactory.failure(String.format("Не удалось обновить пользоватля %s", userDto.username)));
            }
        }
        return ResponseEntity.ok(responseDtoFactory.failure("Данное действие недопустипо для данного уровня прав"));
    }

}
