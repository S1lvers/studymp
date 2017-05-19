package com.studymp.domain.utils;

/**
 * Created by qwerty on 19.05.2017.
 */
import org.springframework.web.util.HtmlUtils;

public class CustomHtmlUtils {
    /**
     * Verify if a string contains any HTML characters by comparing its
     * HTML-escaped version with the original.
     * @param input input  the input String
     * @return boolean True if the String contains HTML characters
     */
    public static boolean isHtml(String input) {
        boolean isHtml = false;
        if (input != null) {
            if (!input.equals(HtmlUtils.htmlEscape(input))) {
                isHtml = true;
            }
        }
        return isHtml;
    }
}