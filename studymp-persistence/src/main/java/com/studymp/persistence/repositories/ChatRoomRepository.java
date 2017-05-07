package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.ChatRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 11.04.2017.
 */
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findByUniqueUsersChatKey(String uniqueUsersChatKey);
}
