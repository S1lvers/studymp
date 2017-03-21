package com.studymp.domain.interfaces;

/**
 * Created by qwerty on 20.03.2017.
 */
public interface EmailSender {
    void Send(String recipientEmail, String title, String message, String SMTP_HOST, String SMTP_PORT, String SMTP_USERNAME, String SMTP_PASSWORD) throws Exception;
    void Send(String recipientEmail, String ccEmail, String title, String message, String SMTP_HOST, String SMTP_PORT, String SMTP_USERNAME, String SMTP_PASSWORD) throws Exception;
}
