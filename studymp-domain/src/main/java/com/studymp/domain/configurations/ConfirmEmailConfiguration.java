package com.studymp.domain.configurations;

import com.studymp.domain.interfaces.EmailConfiguration;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by qwerty on 20.03.2017.
 */
@Component(value = "confirmEmail")
public class ConfirmEmailConfiguration implements EmailConfiguration {
    private static final Logger LOGGER = Logger.getLogger(ConfirmEmailConfiguration.class);

    private static final String CONFIG_PATH = "/confirmMail.properties";
    private static final String CONFIRM_EMAIL_URL = "url.confirm";
    private static final String LOGIN_USERNAME = "username";
    private static final String LOGIN_PASSWORD = "password";
    private static final String SMTP_HOST = "mail.smtp.host";
    private static final String SMTP_PORT = "mail.smtp.port";
    private static final String MAX_EXPIRED_TIME = "max.expired.time";
    private static final String HTML_TEMPLATE_PATH = "html.template.path";

    private String confirmAccount;
    private String username;
    private String password;
    private String smtpHost;
    private String smtpPort;
    private Integer maxExpiredTime;
    private String htmlTemplatePath;

    public ConfirmEmailConfiguration() {
        try {
            Properties properties = PropertiesLoaderUtils.loadProperties(new ClassPathResource(CONFIG_PATH));
            confirmAccount = properties.getProperty(CONFIRM_EMAIL_URL);
            username = properties.getProperty(LOGIN_USERNAME);
            password = properties.getProperty(LOGIN_PASSWORD);
            smtpHost = properties.getProperty(SMTP_HOST);
            smtpPort = properties.getProperty(SMTP_PORT);
            htmlTemplatePath = properties.getProperty(HTML_TEMPLATE_PATH);
            maxExpiredTime = Integer.parseInt(properties.getProperty(MAX_EXPIRED_TIME));

        } catch (IOException e) {
            LOGGER.error(String.format("Failed attempt to load .properties com.studymp.domain from %s due to an unhandled IOException", CONFIG_PATH), e);
        }
    }
    public String getUrl() {
        return confirmAccount;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getSmtpHost() {
        return smtpHost;
    }

    @Override
    public String getSmtpPort() {
        return smtpPort;
    }

    @Override
    public Integer getExpiryTimeout() {
        return maxExpiredTime;
    }

    @Override
    public String getHtmlTemplatePath() {
        return htmlTemplatePath;
    }
}
