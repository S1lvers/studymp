package com.studymp.presentation.interfaces;

import com.studymp.domain.interfaces.Validation;
import com.studymp.presentation.dto.UserDto;

/**
 * Created by qwerty on 22.03.2017.
 */
public interface EmailValidator {
    Validation validateReset(String email);
}
