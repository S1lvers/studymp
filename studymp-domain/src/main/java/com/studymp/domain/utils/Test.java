package com.studymp.domain.utils;

import org.apache.commons.validator.routines.EmailValidator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by qwerty on 20.03.2017.
 */
public class Test {
    public static void main(String[] args) {
        String email = "a.gribushka@pflb.ru";
        System.out.println(EmailValidator.getInstance().isValid(email));
    }
}
