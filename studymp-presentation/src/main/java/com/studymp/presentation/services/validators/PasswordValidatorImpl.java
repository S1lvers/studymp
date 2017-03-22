package com.studymp.presentation.services.validators;

import com.studymp.domain.interfaces.Validation;
import com.studymp.domain.utils.validation.DependantValidation;
import com.studymp.domain.utils.validation.FailCondition;
import com.studymp.domain.utils.validation.GroupValidation;
import com.studymp.presentation.interfaces.PasswordValidator;
import org.springframework.stereotype.Component;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 22.03.2017.
 */
@Component
public class PasswordValidatorImpl implements PasswordValidator {

    @Override
    public Validation validate(String password) {
        return new GroupValidation(
                new FailCondition(
                        () -> password.length() < 8,
                        "Пароль должен быть больше 8 символов"
                )
        );
    }
}
