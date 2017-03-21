package com.studymp.domain.interfaces;

import com.studymp.persistence.entity.User;

/**
 * Created by qwerty on 20.03.2017.
 */
public interface EmailHashLink {
    /**
     * Отправить пользователю ссылку на изменение пароля
     * @param user пользователь, которому отправлять ссылку
     */
    void emailLink(User user) throws Exception;

    /**
     * @param hash хэш, который может быть ассоциирован с пользователем
     * @return имя пользователя, если с указанным хэшом был ассоциирован пользователь
     * и срок жизни хэша не истек
     * @throws Exception для данного хэша нет активной ассоциации с пользователем
     */
    String getUsernameForHash(String hash) throws Exception;
}
