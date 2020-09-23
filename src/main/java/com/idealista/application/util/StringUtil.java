package com.idealista.application.util;

import org.springframework.util.StringUtils;

import java.text.Normalizer;

public final class StringUtil {

    public static String normalizeDiacritics(String string) {
        return Normalizer.normalize(defaultIfEmpty(string, ""), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static String[] stringToWords(String string) {
        if (!StringUtils.hasText(string)) return new String[] {};

        return string.trim().split("\\W+");
    }

    public static String defaultIfEmpty(String string, String defaultValue) {
        return StringUtils.hasText(string) ? string : defaultValue;
    }

}
