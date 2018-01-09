package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.ChatMessageMapper;
import com.studymp.domain.interfaces.ChatMessageService;
import com.studymp.domain.model.ChatMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;
import static org.apache.commons.lang.StringEscapeUtils.escapeJavaScript;

@Controller
public class MessageController {

    private SimpMessagingTemplate template;

    private final ChatMessageMapper chatMessageMapper;
    private final ChatMessageService chatMessageService;

    @Autowired
    public MessageController(SimpMessagingTemplate template, ChatMessageMapper chatMessageMapper,
                             ChatMessageService chatMessageService) {
        this.template = template;
        this.chatMessageMapper = chatMessageMapper;
        this.chatMessageService = chatMessageService;
    }

    @MessageMapping("/chat")
    public void greeting(Message<Object> message, @Payload ChatMessageModel chatMessageModel) throws Exception {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        String authedSender = principal.getName();
        chatMessageModel.setSender(authedSender);
        String recipient = chatMessageModel.getRecipient();
        String msg = chatMessageModel.getMessage();
        chatMessageModel.setMessage(escapeHtml(msg));
        chatMessageService.create(chatMessageMapper.map(chatMessageModel));
        if (!authedSender.equals(recipient)) {
            template.convertAndSendToUser(authedSender, "/queue/messages", chatMessageModel);
        }
        template.convertAndSendToUser(recipient, "/queue/messages", chatMessageModel);
    }

}