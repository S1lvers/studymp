package com.studymp.domain.services;

import com.studymp.domain.interfaces.UserService;
import com.studymp.persistence.entity.User;
import com.studymp.persistence.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 14.03.2017.
 */
@Component
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public User findByUsername(String username) throws Exception {
        User result = seq(userRepository.findByUsername(username))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.error("Не удалось найти пользователя с именем " + username);
                    return new Exception();
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
        Long id = userRepository.save(user).getId();
        userRepository.flush();
        return id;
    }

    //TODO WIP
    @Override
    public void update(User user) {

    }

    //TODO WIP
    @Override
    public void delete(Long id) {

    }
}
