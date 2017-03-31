package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.Role;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.RoleRepository;
import com.studymp.persistence.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 14.03.2017.
 */
@Component
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public User find(Long id) throws Exception {
        User result = userRepository.findOne(id);
        if (result == null) {
            LOGGER.error("Не удалось найти пользователя с id " + id);
            throw new Exception();
        }
        return result;
    }

    @Override
    public User findByUsername(String username) throws NotFoundException {
        User result = seq(userRepository.findByUsername(username))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти пользователя с именем " + username);
                    return new NotFoundException();
                });
        return result;
    }

    @Override
    public User findByEmail(String email) throws Exception {
        User result = seq(userRepository.findByEmail(email))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти пользователя с почтой " + email);
                    return new Exception();
                });
        return result;
    }

    @Transactional
    @Override
    public Long create(User user) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findOne(1L));
        user.setRoles(roles);
        user.setEnabled(false);
        Long id = userRepository.save(user).getId();
        userRepository.flush();
        return id;
    }

    @Transactional
    @Override
    public void update(User user) {
        userRepository.save(user);
        userRepository.flush();
    }

    //TODO WIP
    @Override
    public void delete(Long id) {

    }

    @Transactional
    @Override
    public void approve(User user) {
        user.setEnabled(true);
        update(user);
    }

    @Override
    public void approve(String username) throws NotFoundException {
        User user = findByUsername(username);
        approve(user);
    }

    @Override
    public List<User> find20First() throws Exception {
        Page<User> users = userRepository.findAll(new PageRequest(1, 20));
        return users.getContent();
    }

    @Override
    @ModelAttribute("users")
    public List<User> findAll() throws Exception {
        return userRepository.findAll();
    }
}
