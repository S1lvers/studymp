package com.studymp.domain.utils.generators;

import java.util.Date;
import java.util.Random;

/**
 * Created by qwerty on 20.03.2017.
 */
public class CharSequenceGenerator {
    private static final Random RNG = new Random(new Date().getTime());
    private static final char[] URL_HASH_CHARS = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPLMKJNHBGVFCDXSAZ".toCharArray();

    private CharSequenceGenerator() {
    }
    /**
     * @return случайный набор символов, вполне похожий на хэш (все равно он долго не живет)
     */
    public static String GenerateUrlHash(){
        return generate(20, URL_HASH_CHARS);
    }

    /**
     * @param length длина возвращаемой строки
     * @return случайный набор символов, вполне похожий на хэш (все равно он долго не живет)
     */
    public static String GenerateUrlHash(int length) {
        return generate(length, URL_HASH_CHARS);
    }

    private static String generate(int length, char[] possibleChars) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(possibleChars[RNG.nextInt(length)]);
        return sb.toString();
    }
}
