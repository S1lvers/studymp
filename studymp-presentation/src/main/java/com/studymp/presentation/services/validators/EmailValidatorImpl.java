package com.studymp.presentation.services.validators;

import com.studymp.domain.interfaces.Validation;
import com.studymp.domain.utils.validation.DependantValidation;
import com.studymp.domain.utils.validation.FailCondition;
import com.studymp.domain.utils.validation.GroupValidation;
import com.studymp.persistence.repositories.UserRepository;
import com.studymp.presentation.interfaces.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 22.03.2017.
 */
@Component
public class EmailValidatorImpl implements EmailValidator {

    private UserRepository userRepository;

    @Autowired
    public EmailValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Validation validateReset(String email) {
        return new GroupValidation(
                new DependantValidation(
                        new FailCondition(
                                () -> !org.apache.commons.validator.routines.EmailValidator.getInstance().isValid(email),
                                "Такого email'а не существует"
                        ),
                        new FailCondition(
                                () -> !seq(userRepository.findByEmail(email)).findFirst().isPresent(),
                                "Пользователь с таким email'ом не существует"
                        )
                )

        );
    }
}
