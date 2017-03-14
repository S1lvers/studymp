package com.studymp.presentation.services;

import com.studymp.domain.utils.generators.HashGenerator;
import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.UserMapping;
import org.springframework.stereotype.Component;

/**
 * Created by qwerty on 14.03.2017.
 */

@Component
public class UserMappingImpl implements UserMapping {
    private final static String UNDEFINED = "Не определено";

    @Override
    public User map(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.email == null ? UNDEFINED : userDto.email);
        user.setUsername(userDto.username);
        user.setPassword(HashGenerator.md5Custom(userDto.password));
        return user;
    }
}
