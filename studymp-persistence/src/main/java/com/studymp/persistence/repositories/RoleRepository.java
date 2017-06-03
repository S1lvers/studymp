package com.studymp.persistence.repositories;

import com.studymp.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by qwerty on 15.03.2017.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByRole (String role);
}
