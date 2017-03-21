package com.studymp.domain.interfaces;

import com.studymp.persistence.entity.User;

/**
 * Created by qwerty on 20.03.2017.
 */
public interface MailFactory {
    void sendResetPasswordEmail(User recipient) throws Exception;
    void sendConfirmEmail(User recipient) throws Exception;
}
