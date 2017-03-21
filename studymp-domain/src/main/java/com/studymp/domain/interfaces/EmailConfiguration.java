package com.studymp.domain.interfaces;

/**
 * Created by qwerty on 20.03.2017.
 */
public interface EmailConfiguration {
    String getUsername();
    String getUrl();
    String getSmtpHost();
    String getSmtpPort();
    String getPassword();
    Integer getExpiryTimeout();
    String getHtmlTemplatePath();
}
