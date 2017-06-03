package com.studymp.domain.interfaces;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.persistence.entity.Role;

/**
 * Created by qwerty on 03.06.2017.
 */
public interface RoleService {
    boolean isRoleExist(String role);
    void createRole(String role);
    Role findRoleByName(String name) throws NotFoundException;
}
