package com.studymp.domain.interfaces;

import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Responder;
import com.studymp.persistence.entity.User;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface ResponderService {
    Long create(Responder responder);
    void delete(Responder responder);
    void delete(Long id);
    Responder findByOrderAndResponder(Order order, User responder) throws Exception;
}
