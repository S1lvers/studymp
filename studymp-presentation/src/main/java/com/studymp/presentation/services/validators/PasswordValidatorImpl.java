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

    private static final String regular = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9@#$%]).{8,}";

    @Override
    public Validation validate(String password) {
        return new GroupValidation(
                new FailCondition(
                        () -> password.matches(regular),
                        "Пароль должен содержать не менее 8 символов," +
                                " из которых минимум 1 спецсимвол или цифра и минимум 1 латинская буква"
                )
        );
    }
}
