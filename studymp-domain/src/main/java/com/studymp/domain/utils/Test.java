package com.studymp.domain.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by qwerty on 20.03.2017.
 */
public class Test {
    public static void main(String[] args) {
        final String username = "zzz.plugin@gmail.com";
        final String password = "zzz.plugin.password";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);
        try {

            Transport t = session.getTransport("smtp");

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("zzz.plugin@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("tosha993@mail.ru"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            t.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    }
