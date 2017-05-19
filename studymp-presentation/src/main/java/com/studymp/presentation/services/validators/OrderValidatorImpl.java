package com.studymp.presentation.services.validators;

import com.studymp.domain.interfaces.Validation;
import com.studymp.domain.utils.CustomHtmlUtils;
import com.studymp.domain.utils.validation.DependantValidation;
import com.studymp.domain.utils.validation.FailCondition;
import com.studymp.domain.utils.validation.GroupValidation;
import com.studymp.presentation.dto.OrderDto;
import com.studymp.presentation.interfaces.OrderValidator;
import org.springframework.stereotype.Component;

import static org.jooq.lambda.Seq.seq;

/**
 * Created by qwerty on 19.05.2017.
 */
@Component
public class OrderValidatorImpl implements OrderValidator {
    @Override
    public Validation validate(OrderDto orderDto) {
        return new GroupValidation(
                new DependantValidation(
                        new FailCondition(
                                () -> CustomHtmlUtils.isHtml(orderDto.description) ,
                                "Запрещено использовать спецсимволы типа <> </> и пр."
                        ),
                        new FailCondition(
                                () -> CustomHtmlUtils.isHtml(orderDto.name),
                                "Запрещено использовать спецсимволы типа <> </> и пр."
                        )
                )
        );
    }
}
