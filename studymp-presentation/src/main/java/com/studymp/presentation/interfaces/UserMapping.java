package com.studymp.presentation.interfaces;

import com.studymp.persistence.entity.User;
import com.studymp.presentation.dto.UserDto;

/**
 * Created by qwerty on 14.03.2017.
 */
public interface UserMapping {
    User map(UserDto userDto);
}
