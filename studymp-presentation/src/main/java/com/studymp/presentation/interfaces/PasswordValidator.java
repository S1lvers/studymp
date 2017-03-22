package com.studymp.presentation.interfaces;

import com.studymp.domain.interfaces.Validation;

/**
 * Created by qwerty on 22.03.2017.
 */
public interface PasswordValidator {
    Validation validate(String password);
}
