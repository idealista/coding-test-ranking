package com.idealista.application.service.rating.impl;

import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChaletDescriptionRatingRuleServiceTest {
    private DescriptionRatingRuleService descriptionRatingRuleService;

    @BeforeEach
    void setUp() {
        descriptionRatingRuleService = new DescriptionRatingRuleService();
    }

    @Test
    void calculateEmptyDescriptionChalet() {
        AdVO ad = new AdVO(1, AdVO.CHALET, "", Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateNullDescriptionChalet() {
        AdVO ad = new AdVO(1, AdVO.CHALET, null, Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateHasDescriptionChalet() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.CHALET, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMinWordsDescriptionChalet() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS);
        AdVO ad = new AdVO(1, AdVO.CHALET, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MIN_WORDS, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxMinusOneWordsDescriptionChalet() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.CHALET, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MIN_WORDS, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxWordsDescriptionChalet() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS);
        AdVO ad = new AdVO(1, AdVO.CHALET, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION + descriptionRatingRuleService.HAS_MAX_WORDS_CHALET, descriptionRatingRuleService.calculate(ad));
    }



    private String generateDescription(int length) {
        String description = "";
        for (int i = 0; i < length; i++) {
            description += "word ";
        }

        return description;
    }
}