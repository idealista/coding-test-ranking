package com.idealista.application.service.rating.impl;

import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FlatDescriptionRatingRuleServiceTest {
    private DescriptionRatingRuleService descriptionRatingRuleService;

    @BeforeEach
    void setUp() {
        descriptionRatingRuleService = new DescriptionRatingRuleService();
    }

    @Test
    void calculateEmptyDescriptionFlat() {
        AdVO ad = new AdVO(1, AdVO.FLAT, "", Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateNullDescriptionFlat() {
        AdVO ad = new AdVO(1, AdVO.FLAT, null, Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateHasDescriptionFlat() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMinWordsDescriptionFlat() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS);
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MIN_WORDS, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxMinusOneWordsDescriptionFlat() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MIN_WORDS, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxWordsDescriptionFlat() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS);
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MAX_WORDS_FLAT, descriptionRatingRuleService.calculate(ad));
    }



    private String generateDescription(int length) {
        String description = "";
        for (int i = 0; i < length; i++) {
            description += "word ";
        }

        return description;
    }
}