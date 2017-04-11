package com.studymp.domain.utils.generators;

import com.studymp.persistence.entity.User;

import java.nio.charset.StandardCharsets;

/**
 * Created by qwerty on 11.04.2017.
 */
public class UniqueKeyGenerator {

    public static String GenerateUserPairKey(User user1, User user2) {
        byte[] txt1 = user1.getEmail().getBytes();
        byte[] txt2 = user2.getEmail().getBytes();
        byte[] res = new byte[txt1.length > txt2.length ? txt1.length : txt2.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = (byte) (txt1[i] ^ txt2[i % txt2.length]);
        }
        return new String(res, StandardCharsets.UTF_16);
    }
}
