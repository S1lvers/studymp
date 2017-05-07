package com.studymp.domain.services;

import com.studymp.domain.interfaces.EmailSender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Created by qwerty on 20.03.2017.
 */

@Component
public class EmailSenderImpl implements EmailSender {
    private static final Logger LOGGER = Logger.getLogger(EmailSenderImpl.class.getName());
    /**
     * Send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param title title of the message
     * @param message message to be sent
     */

    @Override
    public void Send(String recipientEmail, String title, String message,
                     String SMTP_HOST, String SMTP_PORT, String SMTP_USERNAME, String SMTP_PASSWORD) throws Exception {
        Send(recipientEmail, "", title, message, SMTP_HOST, SMTP_PORT, SMTP_USERNAME, SMTP_PASSWORD);
    }

    /**
     * Send email using GMail SMTP server.
     *
     * @param recipientEmail TO recipient
     * @param ccEmail CC recipient. Can be empty if there is no CC recipient
     * @param title title of the message
     * @param messageContent message to be sent
     */
    @Override
    public void Send(String recipientEmail, String ccEmail, String title, String messageContent,
                     String SMTP_HOST, String SMTP_PORT, String SMTP_USERNAME, String SMTP_PASSWORD) throws Exception{

        System.setProperty("https.protocols", "TLSv1.1");

        final Properties config = new Properties();
        config.put("mail.transport.protocol", "smtp");
        config.put("mail.smtp.auth", "true");
        config.put("mail.smtp.starttls.enable", "true");
        config.put("mail.smtp.host", SMTP_HOST);
        config.put("mail.smtp.port", SMTP_PORT);

        final Session session = Session.getInstance(config, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
        try {
            final MimeMessage message = new MimeMessage(session);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipientEmail));
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setSubject(title);
            message.setText(messageContent, "utf-8", "html");
            message.setSentDate(new Date());
            LOGGER.info(String.format("Trying to send message to %s ...", recipientEmail));
            Transport.send(message);
            LOGGER.info("Email was successfully sent!");
        } catch (MessagingException ex) {
            LOGGER.debug(ex);
            LOGGER.error("An error occurred while trying to send message  ");
        }
    }
}
