package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.ChatMessageService;
import com.studymp.domain.interfaces.ChatRoomService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.utils.generators.UniqueKeyGenerator;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

/**
 * Created by qwerty on 14.04.2017.
 */
@Controller
@RequestMapping("/user")
public class ChatController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @Autowired
    public ChatController(ChatRoomService chatRoomService, ChatMessageService chatMessageService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.userService = userService;
    }

    @RequestMapping(
            value = "/chat",
            method = RequestMethod.GET)
    public String getChatHistory(Model model, @RequestParam("destination") String destination) {
        try {
            User principal = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            User destinationUser = userService.findByUsername(destination);

            PageRequest request = new PageRequest(0, 100, Sort.Direction.DESC, "createDate");
            String key = UniqueKeyGenerator.GenerateUserPairKey(principal, destinationUser);
            ChatRoom chatRoom = chatRoomService.findByUniqueUsersPairKey(key);
            if (chatRoom == null) {
                Long chatRoomId = chatRoomService.create(new ChatRoom(key));
            }
            List<ChatMessage> chatMessages = chatMessageService.findByChatRoomWithPageable(chatRoom, request);
            chatMessages.forEach(x -> System.out.println(x.toString()));
            model.addAttribute("messages", chatMessages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chat";
    }
}
