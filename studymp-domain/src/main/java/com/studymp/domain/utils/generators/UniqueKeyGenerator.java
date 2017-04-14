package com.studymp.domain.utils.generators;


import com.studymp.persistence.entity.User;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qwerty on 11.04.2017.
 */
public class UniqueKeyGenerator {

    private static final Logger LOGGER = Logger.getLogger(UniqueKeyGenerator.class);

    public static String GenerateUserPairKey(User user1, User user2) {

        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("Не найден алгоритм хэширования MD5");
            LOGGER.debug(e);
        }

        byte[] txt1 = messageDigest.digest(user1.getEmail().getBytes());
        byte[] txt2 = messageDigest.digest(user2.getEmail().getBytes());

        byte[] res = new byte[txt1.length > txt2.length ? txt1.length : txt2.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (byte) (txt1[i] ^ txt2[i % txt2.length]);
        }
        return new String(res, StandardCharsets.UTF_16);
    }
}
