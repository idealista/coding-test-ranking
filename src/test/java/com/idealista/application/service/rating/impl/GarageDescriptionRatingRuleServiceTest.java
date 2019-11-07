package com.idealista.application.service.rating.impl;

import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GarageDescriptionRatingRuleServiceTest {
    private DescriptionRatingRuleService descriptionRatingRuleService;

    @BeforeEach
    void setUp() {
        descriptionRatingRuleService = new DescriptionRatingRuleService();
    }

    @Test
    void calculateEmptyDescriptionGarage() {
        AdVO ad = new AdVO(1, AdVO.GARAGE, "", Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateNullDescriptionGarage() {
        AdVO ad = new AdVO(1, AdVO.GARAGE, null, Arrays.asList(1), 300, null, null, null);

        assertEquals(0, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateHasDescriptionGarage() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.GARAGE, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMinWordsDescriptionGarage() {
        String description = generateDescription(descriptionRatingRuleService.MIN_WORDS);
        AdVO ad = new AdVO(1, AdVO.GARAGE, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxMinusOneWordsDescriptionGarage() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS - 1);
        AdVO ad = new AdVO(1, AdVO.GARAGE, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMaxWordsDescriptionGarage() {
        String description = generateDescription(descriptionRatingRuleService.MAX_WORDS);
        AdVO ad = new AdVO(1, AdVO.GARAGE, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(descriptionRatingRuleService.HAS_DESCRIPTION, descriptionRatingRuleService.calculate(ad));
    }



    private String generateDescription(int length) {
        String description = "";
        for (int i = 0; i < length; i++) {
            description += "word ";
        }

        return description;
    }
}