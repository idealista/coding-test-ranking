package com.idealista.application.service.rating.impl;

import com.idealista.application.service.domain.SpecialWords;
import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SpecialWordsRatingRuleServiceTest {
    SpecialWordsRatingRuleService specialWordsRatingRuleService;

    @BeforeEach
    void setUp() {
        specialWordsRatingRuleService = new SpecialWordsRatingRuleService();
    }

    @Test
    void calculateNullDescription() {
        AdVO ad = new AdVO(1, AdVO.FLAT, null, Arrays.asList(1), 300, null, null, null);

        assertEquals(0, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateEmptyDescription() {
        AdVO ad = new AdVO(1, AdVO.FLAT, "", Arrays.asList(1), 300, null, null, null);

        assertEquals(0, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateNoSpecialWordsDescription() {
        AdVO ad = new AdVO(1, AdVO.FLAT, "abc def ghi", Arrays.asList(1), 300, null, null, null);

        assertEquals(0, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateOneSpecialWordDescription() {
        String description = "abc def ghi" + SpecialWords.CENTRICO.name();
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(specialWordsRatingRuleService.HAS_WORD, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateThreeSpecialWordDescription() {
        String description = "abc " + SpecialWords.ETICO.name() + "def" + SpecialWords.NUEVO.name() + "ghi" + SpecialWords.CENTRICO.name();
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(specialWordsRatingRuleService.HAS_WORD * 3, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateRepeatedSpecialWordDescription() {
        String description = "abc " + SpecialWords.ETICO.name() + "def" + SpecialWords.ETICO.name();
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(specialWordsRatingRuleService.HAS_WORD, specialWordsRatingRuleService.calculate(ad));
    }

    @Test
    void calculateWithDiacriticSpecialWordDescription() {
        String description = "abc ético NúÉvó";
        AdVO ad = new AdVO(1, AdVO.FLAT, description, Arrays.asList(1), 300, null, null, null);

        assertEquals(specialWordsRatingRuleService.HAS_WORD * 2, specialWordsRatingRuleService.calculate(ad));
    }
}