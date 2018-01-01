package com.studymp.domain.utils;

import com.studymp.domain.interfaces.ActiveUserService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Set;

public class ActiveUserPinger {

    private SimpMessagingTemplate template;
    private ActiveUserService activeUserService;

    public ActiveUserPinger(SimpMessagingTemplate template, ActiveUserService activeUserService) {
        this.template = template;
        this.activeUserService = activeUserService;
    }

    @Scheduled(fixedDelay = 5000)
    public void pingUsers() {
        Set<String> activeUsers = activeUserService.getActiveUsers();
        template.convertAndSend("/topic/active", activeUsers);
    }

}
