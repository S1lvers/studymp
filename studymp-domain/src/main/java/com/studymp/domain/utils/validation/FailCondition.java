package com.studymp.domain.utils.validation;

import com.studymp.domain.interfaces.Validation;

import java.util.function.Supplier;

/**
 * Created by qwerty on 14.03.2017.
 */
public class FailCondition implements Validation {
    private final Supplier<Boolean> evaluation;
    private final String errorMessage;

    public FailCondition(Supplier<Boolean> failConditionSupplier, String errorMessage) {
        this.evaluation = failConditionSupplier;
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean isValid() {
        return !evaluation.get();
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
