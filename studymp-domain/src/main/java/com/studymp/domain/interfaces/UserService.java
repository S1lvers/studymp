package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.User;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


/**
 * Created by qwerty on 14.03.2017.
 */
public interface UserService {
    User find(Long id) throws Exception;
    User findByUsername(String username) throws NotFoundException;
    User findByEmail(String email) throws Exception;
    Long create(User user);
    void update(User user);
    void delete(Long id);
    void approve(User user);
}
