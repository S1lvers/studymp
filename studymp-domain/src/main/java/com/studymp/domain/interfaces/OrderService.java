package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Section;
import com.studymp.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface OrderService {
    Order find(Long id) throws Exception;
    Order findByNameAndCreator(String username, User creator) throws NotFoundException;
    Long create(Order order);
    void update(Order order);
    void delete(Long id);
    List<Order> findBySection(Section section) throws Exception;
}
