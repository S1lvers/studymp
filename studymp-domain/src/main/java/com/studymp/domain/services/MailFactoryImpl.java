package com.studymp.domain.services;

import com.studymp.domain.interfaces.MailFactory;
import com.studymp.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by qwerty on 20.03.2017.
 */
@Component
public class MailFactoryImpl implements MailFactory {

    private final ResetPasswordImpl resetPassword;
    private final ConfirmEmailImpl confirmEmail;

    @Autowired
    public MailFactoryImpl(ResetPasswordImpl resetPassword, ConfirmEmailImpl confirmEmail) {
        this.resetPassword = resetPassword;
        this.confirmEmail = confirmEmail;
    }

    @Override
    public void sendResetPasswordEmail(User recipient) throws Exception {
        resetPassword.emailLink(recipient);
    }

    @Override
    public void sendConfirmEmail(User recipient) throws Exception {
        confirmEmail.emailLink(recipient);
    }
}
