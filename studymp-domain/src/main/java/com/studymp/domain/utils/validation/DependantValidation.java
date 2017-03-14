package com.studymp.domain.utils.validation;

import com.studymp.domain.interfaces.Validation;

/**
 * Created by qwerty on 14.03.2017.
 */
public class DependantValidation implements Validation {
    private final static String NEW_LINE = java.lang.System.getProperty("line.separator");

    private final boolean valid;
    private final String errorMessage;

    public DependantValidation(Validation required, Validation dependant) {
        StringBuilder errors = new StringBuilder();
        if (!required.isValid()) {
            errors.append(NEW_LINE).append(required.getErrorMessage());
        } else if (!dependant.isValid()) {
            errors.append(NEW_LINE).append(dependant.getErrorMessage());
        }
        valid = errors.length() == 0;
        errorMessage = errors.toString();
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
