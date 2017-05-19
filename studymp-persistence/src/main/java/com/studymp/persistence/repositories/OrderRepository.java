package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.Order;
import com.studymp.persistence.entity.Section;
import com.studymp.persistence.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 07.05.2017.
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByNameAndCreator(String name, User creator);
    List<Order> findBySection(Section section);
}
