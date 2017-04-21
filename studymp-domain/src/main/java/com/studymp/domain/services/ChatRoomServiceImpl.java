package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.ChatRoomService;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.repositories.ChatRoomRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 11.04.2017.
 */
@Component
public class ChatRoomServiceImpl implements ChatRoomService {

    private static final Logger LOGGER = Logger.getLogger(ChatRoomServiceImpl.class);

    private final ChatRoomRepository chatRoomRepository;

    @Autowired
    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public ChatRoom find(Long id) throws Exception {
        ChatRoom result = chatRoomRepository.findOne(id);
        if (result == null) {
            LOGGER.error("Не удалось найти чат-комнату с id " + id);
            throw new Exception();
        }
        return result;
    }

    @Override
    public ChatRoom findByUniqueUsersPairKey(String uniqueUsersPairKey) throws NotFoundException {
        Optional<ChatRoom> result = seq(chatRoomRepository.findByUniqueUsersChatKey(uniqueUsersPairKey))
                .findFirst();
        if (result.isPresent()) {
            return result.get();
        } else return null;
    }

    @Transactional
    @Override
    public Long create(ChatRoom chatRoom) {
        Long id = chatRoomRepository.save(chatRoom).getId();
        chatRoomRepository.flush();
        return id;
    }

}
