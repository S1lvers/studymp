package com.studymp.presentation.services;

import com.studymp.domain.utils.generators.HashGenerator;
import com.studymp.persistence.entity.Role;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.UserMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by qwerty on 14.03.2017.
 */

@Component
public class UserMappingImpl implements UserMapping {
    private final static String UNDEFINED = "Не определено";

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User map(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.email == null ? UNDEFINED : userDto.email);
        user.setUsername(userDto.username);
        user.setPassword(passwordEncoder.encode(userDto.password));
        /*user.setApproved(false);*/
        return user;
    }
}
