package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.ChatMessage;
import com.studymp.persistence.entity.ChatRoom;
import com.studymp.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 11.04.2017.
 */
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    Page<ChatMessage> findAllByAuthorAndDestination(User author, User destination, Pageable pageable);
    Page<ChatMessage> findByChatRoom(ChatRoom chatRoom, Pageable pageable);
    List<ChatMessage> findByChatRoom(ChatRoom chatRoom);
}
