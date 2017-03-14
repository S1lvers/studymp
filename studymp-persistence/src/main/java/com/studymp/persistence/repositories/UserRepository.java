package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by qwerty on 14.03.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    List<User> findByEmail(String email);
}
