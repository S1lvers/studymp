package com.studymp.presentation.services;

import com.studymp.domain.utils.validation.DependantValidation;
import com.studymp.domain.utils.validation.FailCondition;
import com.studymp.domain.utils.validation.GroupValidation;
import com.studymp.persistance.repositories.UserRepository;
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

    private final UserRepository userRepository;

    @Autowired
    public UserValidatorImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public Validation validatePutDto(UserDto userDto) {
        return new GroupValidation(
                new FailCondition(
                        () -> userDto.email == null || userDto.email.equals(""),
                        "Почта пользователя не может быть пустой"
                ),
                new DependantValidation(
                        new FailCondition(
                                () -> userDto.email == null || userDto.email.equals(""),
                                "Юзернейм пользователя не может быть пустым"
                        ),
                        new FailCondition(
                                () -> seq(userRepository.findByEmail(userDto.email)).findFirst().isPresent(),
                                "Пользователь с таким юзернеймом уже существует"
                        )
                )
        );
    }
}
