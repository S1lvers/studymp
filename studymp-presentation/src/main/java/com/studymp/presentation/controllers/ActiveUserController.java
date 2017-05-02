package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.ActiveUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ActiveUserController {

    private ActiveUserService activeUserService;

    @Autowired
    public ActiveUserController(ActiveUserService activeUserService) {
        this.activeUserService = activeUserService;
    }

    @MessageMapping("/activeUsers")
    public void activeUsers(Message<Object> message) {
        Principal user = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        activeUserService.mark(user.getName());
    }

}
