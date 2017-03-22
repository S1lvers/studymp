package com.studymp.presentation.services.validators;

import com.studymp.domain.utils.validation.DependantValidation;
import com.studymp.domain.utils.validation.FailCondition;
import com.studymp.domain.utils.validation.GroupValidation;
import com.studymp.persistence.repositories.UserRepository;
import com.studymp.presentation.dto.UserDto;
import com.studymp.presentation.interfaces.UserValidator;
import com.studymp.domain.interfaces.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 14.03.2017.
 */
@Component
public class UserValidatorImpl implements UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Validation validatePutDto(UserDto userDto) {
        return new GroupValidation(
                new FailCondition(
                        () -> userDto.password == null || (userDto.password.length() < 8),
                        "Пароль должен содержать не меньше 8 символов"
                ),
                new DependantValidation(
                        new FailCondition(
                                () -> userDto.email == null || userDto.email.equals(""),
                                "Email пользователя не может быть пустым"
                        ),
                        new FailCondition(
                                () -> seq(userRepository.findByEmail(userDto.email)).findFirst().isPresent(),
                                "Пользователь с таким email'ом уже существует"
                        )
                ),
                new DependantValidation(
                        new FailCondition(
                                () -> userDto.username == null || userDto.username.equals(""),
                                "Юзернейм пользователя не может быть пустым"
                        ),
                        new FailCondition(
                                () -> seq(userRepository.findByUsername(userDto.username)).findFirst().isPresent(),
                                "Пользователь с таким юзернеймом уже существует"
                        )
                )
        );
    }
}
