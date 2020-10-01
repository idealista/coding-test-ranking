package com.idealista.infrastructure.utils;

import java.text.Normalizer;

public class StringFormatterUtils {

    private static final String ACCENT_UNICODE_REGEX = "\\p{M}";
    private static final String NON_ALPHABETIC_CHARACTERS_REGEX = "[^a-zA-Z]";
    private static final String ONE_OR_MORE_SPACES_REGEX = " +";
    private static final String EMPTY_CHARACTER = "";
    private static final String SPACE_CHARACTER = " ";

    /**
     * Normalizes the input string by removing accents, converting it to lower case, replacing all non-alphabetic
     * characters by spaces, trimming spaces and removing extra spaces.
     *
     * @param input
     * @return
     */
    public static String normalize(String input){
        String normalizedKeyword = Normalizer.normalize(input, Normalizer.Form.NFD);
        return normalizedKeyword
            .toLowerCase()
            .replaceAll(ACCENT_UNICODE_REGEX, EMPTY_CHARACTER)
            .replaceAll(NON_ALPHABETIC_CHARACTERS_REGEX, SPACE_CHARACTER)
            .replaceAll(ONE_OR_MORE_SPACES_REGEX, SPACE_CHARACTER)
            .trim();
    }
}
