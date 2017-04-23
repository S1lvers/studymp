package com.studymp.presentation.controllers;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.ChatMessageService;
import com.studymp.domain.interfaces.ChatRoomService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.utils.generators.UniqueKeyGenerator;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.MessageDto;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.ResponseDtoFactory;
import org.apache.log4j.Logger;
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

import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by qwerty on 14.04.2017.
 */
@Controller
@RequestMapping("/user")
public class ChatController {
    private static final Logger LOGGER = Logger.getLogger(ChatController.class);
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;
    private final ResponseDtoFactory responseDtoFactory;

    @Autowired
    public ChatController(ChatRoomService chatRoomService, ChatMessageService chatMessageService, UserService userService, ResponseDtoFactory responseDtoFactory) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.userService = userService;
        this.responseDtoFactory = responseDtoFactory;
    }

    // /chat?destination=username1
    @RequestMapping(
            value = "/chat",
            method = RequestMethod.GET)
    public String getChatHistory(Model model, @RequestParam("destination") String destinationUsername) {
        try {
            User principalUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            User destinationUser = userService.findByUsername(destinationUsername);
            ChatRoom chatRoom = this.getChatRoom(principalUser, destinationUser);
            PageRequest request = new PageRequest(0, 100, Sort.Direction.DESC, "createDate");
            List<ChatMessage> chatMessages = chatMessageService.findByChatRoomWithPageable(chatRoom, request);
            model.addAttribute("messages", chatMessages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "chat";
    }

    @RequestMapping(
            value = "/chat",
            method = RequestMethod.PUT)
    public ResponseEntity putChatMessage(@RequestBody MessageDto message) {
        try {
            User principalUser = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            User destinationUser = userService.findByUsername(message.destinationUsername);
            ChatRoom chatRoom = this.getChatRoom(principalUser, destinationUser);
            chatMessageService.create(new ChatMessage(message.text, principalUser, destinationUser, chatRoom, new Date()));
            return ResponseEntity.ok(responseDtoFactory.success());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(responseDtoFactory.failure(String.format("Что то пошло не так, обновите страницу и попробуйте снова")));
        }
    }

    private ChatRoom getChatRoom(User principalUser, User destinationUser) throws NotFoundException{
        try {
            String key = UniqueKeyGenerator.GenerateUserPairKey(principalUser, destinationUser);
            ChatRoom chatRoom = chatRoomService.findByUniqueUsersPairKey(key);
            if (chatRoom == null) {
                chatRoom = new ChatRoom(key);
                chatRoomService.create(chatRoom);
            }
            return chatRoom;
        }
        catch (Exception e){
            LOGGER.error(String.format("Не удалось создать chatRoom"));
            throw new NotFoundException("Не удалось создать chatRoom");
        }
    }
}
