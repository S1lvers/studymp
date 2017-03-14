package com.studymp.domain.utils.validation;

import com.studymp.domain.interfaces.Validation;

/**
 * Created by qwerty on 14.03.2017.
 */
public class GroupValidation implements Validation {
    private final static String NEW_LINE = java.lang.System.getProperty("line.separator");

    private final boolean valid;
    private final String errorMessage;

    public GroupValidation(Validation... validations) {
        StringBuilder errors = new StringBuilder();
        for (Validation validation : validations)
            if (!validation.isValid())
                errors.append(NEW_LINE).append(validation.getErrorMessage());
        this.valid = errors.length() == 0;
        this.errorMessage = errors.toString();
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
