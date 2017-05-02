package com.studymp.domain.interfaces;

import java.util.Set;

/**
 * Created by qwerty on 24.04.2017.
 */
public interface ActiveUserService {
    void mark(String username);
    Set<String> getActiveUsers();
}
