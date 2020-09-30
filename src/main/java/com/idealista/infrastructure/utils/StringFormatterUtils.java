package com.idealista.infrastructure.utils;

import java.text.Normalizer;

public class StringFormatterUtils {

    private static final String ACCENT_UNICODE_REGEX = "\\p{M}";
    private static final String EMPTY_CHARACTER = "";

    public static String normalize(String input){
        String normalizedKeyword = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalizedKeyword.toLowerCase().replaceAll(ACCENT_UNICODE_REGEX, EMPTY_CHARACTER);
    }
}
