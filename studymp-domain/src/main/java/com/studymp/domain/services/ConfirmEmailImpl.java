package com.studymp.domain.services;

import com.studymp.domain.exceptions.NotFoundException;
import com.studymp.domain.interfaces.EmailConfiguration;
import com.studymp.domain.interfaces.EmailHashLink;
import com.studymp.domain.interfaces.EmailSender;
import com.studymp.domain.utils.generators.CharSequenceGenerator;
import com.studymp.persistence.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

import static org.jooq.lambda.Seq.seq;


/**
 * Created by qwerty on 20.03.2017.
 */
@Component
public class ConfirmEmailImpl implements EmailHashLink {
    private static final String TITLE = "Подтверждение аккаунта";
    private static final Integer MILLISECONDS_IN_MINUTE = 60000;

    private List<UserAssociation> userAssociationList = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(ResetPasswordImpl.class);

    private final EmailSender emailSender;

    private final EmailConfiguration emailConfiguration;

    @Autowired
    public ConfirmEmailImpl(EmailSender emailSender, @Qualifier("confirmEmail") EmailConfiguration emailConfiguration) {
        this.emailSender = emailSender;
        this.emailConfiguration = emailConfiguration;
    }

    @Override
    public void emailLink(User user) throws Exception {
        expireAssociations();
        String url = emailConfiguration.getUrl();
        File file = new ClassPathResource(emailConfiguration.getHtmlTemplatePath()).getFile();
        String htmlText = new Scanner(file)
                .useDelimiter("\\Z").next();
        userAssociationList.removeIf(p -> p.user.equals(user));
        UserAssociation userAssociation = new UserAssociation();
        userAssociation.user = user;
        userAssociation.created = new Date();
        userAssociation.hash = CharSequenceGenerator.GenerateUrlHash();
        String urlWithHash = url + "?hash="+userAssociation.hash;
        String newHtmlText = htmlText
                .replaceAll("\\$confirm_account_link", urlWithHash)
                .replaceAll("\\$user_name", user.getUsername());

        emailSender.Send(user.getEmail(), TITLE, newHtmlText,
                emailConfiguration.getSmtpHost(), emailConfiguration.getSmtpPort(),
                emailConfiguration.getUsername(), emailConfiguration.getPassword());

        userAssociationList.add(userAssociation);
    }

    @Override
    public String getUsernameForHash(String hash) throws Exception {
        expireAssociations();
        Optional<UserAssociation> userAssociationOptinal =
                seq(userAssociationList).findFirst(x -> x.hash.equals(hash));
        if (!userAssociationOptinal.isPresent())
            throw new NotFoundException("Не удалось найти пользователя для хэша подтверждения аккаунту " + hash);
        UserAssociation userAssociation = userAssociationOptinal.get();
        userAssociationList.remove(userAssociation);
        return userAssociation.user.getUsername();
    }

    private void expireAssociations() {
        Long currentTime = new Date().getTime();
        Long minUnexpiredTime = currentTime - emailConfiguration.getExpiryTimeout() * MILLISECONDS_IN_MINUTE;
        Date minUnexpiredDate = new Date(minUnexpiredTime);
        List<UserAssociation> expiredAssociations = seq(userAssociationList)
                .filter(ua -> ua.created.before(minUnexpiredDate))
                .toList();
        for (UserAssociation userAssociation : expiredAssociations) {
            LOGGER.debug(String.format("Истёк срок жизни ссылки на подтверждение аккаунта для пользователя %s с солью %s",
                    userAssociation.user.getUsername(), userAssociation.hash));
            userAssociationList.remove(userAssociation);
        }
    }

    private static class UserAssociation {
        String hash;
        User user;
        Date created;
    }
}
