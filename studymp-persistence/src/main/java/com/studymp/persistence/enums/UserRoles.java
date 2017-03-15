package com.studymp.persistence.enums;

/**
 * Created by qwerty on 15.03.2017.
 */
public enum UserRoles {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");

    private final String text;

    /**
     * @param text
     */
    private UserRoles(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
