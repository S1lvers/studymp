package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by qwerty on 11.04.2017.
 */
public interface ChatMessageService {
    ChatMessage find(Long id) throws Exception;
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom) throws NotFoundException;
    List<ChatMessage> findByChatRoomWithPageable(ChatRoom chatRoom, Pageable pageable) throws NotFoundException;
    Long create(ChatMessage chatMessage);
}
