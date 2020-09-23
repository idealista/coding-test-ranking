package com.idealista.application.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilTest {

    @Test
    public void shouldReturnNullWhenCallNormalizeDiacriticsWithNullString() {
        assertThat(StringUtil.normalizeDiacritics(null)).isEmpty();
    }

    @Test
    public void shouldReturnNullWhenCallNormalizeDiacriticsWithEmptyString() {
        assertThat(StringUtil.normalizeDiacritics("")).isEmpty();
    }

    @Test
    public void shouldNormalizeDiacritics() {
        final String string = "áàäâã éèëê íìïî óòöôõ úùüûű ÁÀÄÂÃ ÉÈËÊ ÍÌÏÎ ÓÒÖÔÕ ÚÙÜÛŰ";
        final String expected = "aaaaa eeee iiii ooooo uuuuu AAAAA EEEE IIII OOOOO UUUUU";

        assertThat(StringUtil.normalizeDiacritics(string)).isEqualTo(expected);
    }

    @Test
    public void shouldReturnEmptyArrayWhenCallStringToWordsWithNullString() {
        assertThat(StringUtil.stringToWords(null)).isEmpty();
    }

    @Test
    public void shouldReturnEmptyArrayWhenCallStringToWordsWithEmptyString() {
        assertThat(StringUtil.stringToWords("")).isEmpty();
    }

    @Test
    public void shouldConvertInputStringToArrayOfWords() {
        final String string = "Chuck Norris can't test for equality because he has no equal.";
        final String stringWithSymbols = "When Chuck Norris presses Ctrl+Alt+Delete, worldwide computer restart is initiated.";

        assertThat(StringUtil.stringToWords(string))
                .hasSize(12)
                .containsExactly("Chuck", "Norris", "can", "t", "test", "for", "equality", "because", "he", "has", "no", "equal");

        assertThat(StringUtil.stringToWords(stringWithSymbols))
                .hasSize(12)
                .containsExactly("When", "Chuck", "Norris", "presses", "Ctrl", "Alt", "Delete", "worldwide", "computer", "restart", "is", "initiated");

    }
}
