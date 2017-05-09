package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Responder;
import com.studymp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface ResponderRepository extends JpaRepository<Responder, Long>{
    List<Responder> findByOrderAndResponder(Order order, User responder);
}
