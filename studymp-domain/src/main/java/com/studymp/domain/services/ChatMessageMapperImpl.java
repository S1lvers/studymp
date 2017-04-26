package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.ChatMessageMapper;
import com.studymp.domain.interfaces.ChatRoomService;
import com.studymp.domain.interfaces.UserService;
import com.studymp.domain.model.ChatMessageModel;
import com.studymp.domain.utils.generators.UniqueKeyGenerator;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by qwerty on 26.04.2017.
 */
@Component
public class ChatMessageMapperImpl implements ChatMessageMapper {

    private final UserService userService;
    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatMessageMapperImpl(UserService userService, ChatRoomService chatRoomService) {
        this.userService = userService;
        this.chatRoomService = chatRoomService;
    }

    @Override
    public ChatMessage map(ChatMessageModel chatMessageModel) throws NotFoundException{
        User author = userService.findByUsername(chatMessageModel.getSender());
        User companion = userService.findByUsername(chatMessageModel.getRecipient());
        ChatRoom chatRoom = chatRoomService.findByUniqueUsersPairKey(UniqueKeyGenerator.GenerateUserPairKey(author, companion));
        if (chatRoom == null){
            chatRoom = chatRoomService.find(chatRoomService.create(new ChatRoom(UniqueKeyGenerator.GenerateUserPairKey(author, companion))));
        }
        return new ChatMessage(chatMessageModel.getMessage(),
                author,
                companion,
                chatRoom,
                new Date());
    }
}
