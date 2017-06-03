package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.RoleService;
import com.studymp.persistence.entity.Role;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.RoleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 03.06.2017.
 */
@Component
public class RoleServiceImpl implements RoleService {

    private final static Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public boolean isRoleExist(String role)  {
        Optional<Role> result = seq(roleRepository.findByRole(role)).findFirst();
        return result.isPresent();
    }

    @Override
    @Transactional
    public void createRole(String role) {
        Role roleEntity = new Role(role);
        roleRepository.saveAndFlush(roleEntity);
    }

    @Override
    public Role findRoleByName(String name) throws NotFoundException{
        Role result = seq(roleRepository.findByRole(name))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти роль с названием " + name);
                    return new NotFoundException();
                });
        return result;
    }
}
