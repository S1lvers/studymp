package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.ChatRoom;

import java.util.List;

/**
 * Created by qwerty on 11.04.2017.
 */
public interface ChatRoomService {
    ChatRoom find(Long id) throws Exception;
    ChatRoom findByUniqueUsersPairKey(String uniqueUsersPairKey) throws NotFoundException;
    Long create(ChatRoom chatRoom);
}
