package com.studymp.presentation.interfaces;

import com.studymp.domain.interfaces.Validation;
import com.studymp.presentation.dto.UserDto;


public interface UserValidator {
    Validation validatePutDto(UserDto userDto);
}
