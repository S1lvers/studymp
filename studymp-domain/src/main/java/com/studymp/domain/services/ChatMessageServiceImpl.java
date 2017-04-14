package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.ChatMessageService;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.repositories.ChatMessageRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 11.04.2017.
 */
@Component
public class ChatMessageServiceImpl implements ChatMessageService {

    private static final Logger LOGGER = Logger.getLogger(ChatMessageServiceImpl.class);

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageServiceImpl(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Override
    public ChatMessage find(Long id) throws Exception {
        ChatMessage result = chatMessageRepository.findOne(id);
        if (result == null) {
            LOGGER.error("Не удалось найти чат с id " + id);
            throw new Exception();
        }
        return result;
    }

    @Override
    public List<ChatMessage> findByChatRoom(ChatRoom chatRoom) throws NotFoundException {
        List<ChatMessage> chat = chatMessageRepository.findByChatRoom(chatRoom);
        return chat;
    }

    @Override
    public List<ChatMessage> findByChatRoomWithPageable(ChatRoom chatRoom, Pageable pageable) throws NotFoundException {
        List<ChatMessage> chat = chatMessageRepository.findByChatRoom(chatRoom, pageable).getContent();
        return chat;
    }

    @Transactional
    @Override
    public Long create(ChatMessage chatMessage) {
        Long id = chatMessageRepository.save(chatMessage).getId();
        chatMessageRepository.flush();
        return id;
    }
}
