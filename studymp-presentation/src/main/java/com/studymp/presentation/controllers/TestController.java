package com.studymp.presentation.controllers;

import com.studymp.domain.interfaces.ChatMessageService;
import com.studymp.domain.interfaces.ChatRoomService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.interfaces.Validation;
import com.studymp.domain.utils.generators.UniqueKeyGenerator;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.ChatMessageRepository;
import com.studymp.presentation.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 11.04.2017.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final UserService userService;

    @Autowired
    public TestController(ChatRoomService chatRoomService, ChatMessageService chatMessageService, UserService userService) {
        this.chatRoomService = chatRoomService;
        this.chatMessageService = chatMessageService;
        this.userService = userService;
    }

    @RequestMapping(
            value = "/mainTest",
            method = RequestMethod.GET)
    public ResponseEntity mainTest(@RequestParam(name = "page") String page) {
        try {
            Integer pageNumber = Integer.parseInt(page);
            PageRequest request = new PageRequest(pageNumber, 5, Sort.Direction.DESC, "createDate");
            String key = UniqueKeyGenerator.GenerateUserPairKey(userService.find(9L), userService.find(8L));
            ChatRoom chatRoom = chatRoomService.findByUniqueUsersPairKey(key);
            if (chatRoom == null){
                Long chatRoomId = chatRoomService.create(new ChatRoom(key));
            } else {
                List<ChatMessage> chat = chatMessageService.findByChatRoomWithPageable(chatRoom, request);
                chat.forEach(x -> System.out.println(x.toString()));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(null);
    }

    class Message {
        private String message;

        public Message(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @RequestMapping(path = "/echo/{message}", method= RequestMethod.GET)
    public Message echo(@PathVariable("message") String message) {
        return new Message(message);
    }
}
