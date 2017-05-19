package com.studymp.presentation.interfaces;

import com.studymp.domain.interfaces.Validation;
import com.studymp.presentation.dto.OrderDto;

/**
 * Created by qwerty on 19.05.2017.
 */
public interface OrderValidator {
    Validation validate (OrderDto order);
}
