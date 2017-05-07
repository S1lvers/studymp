package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.model.ChatMessageModel;
import com.studymp.persistence.entity.ChatMessage;

/**
 * Created by qwerty on 26.04.2017.
 */
public interface ChatMessageMapper {
    ChatMessage map(ChatMessageModel chatMessageModel) throws NotFoundException;
}
