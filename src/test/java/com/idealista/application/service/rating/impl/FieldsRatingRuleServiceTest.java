package com.idealista.application.service.rating.impl;

import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class FieldsRatingRuleServiceTest {
    private FieldsRatingRuleService fieldsRatingRuleService;
    private AdVO incompleteFlat;
    private AdVO completeFlat;
    private AdVO incompleteChalet;
    private AdVO completeChalet;
    private AdVO incompleteGarage;
    private AdVO completeGarage;

    @BeforeEach
    void setUp() {
        fieldsRatingRuleService = new FieldsRatingRuleService();

        incompleteFlat = new AdVO(1, AdVO.FLAT, "Desc", Collections.<Integer>emptyList(), 300, null, null, null);
        completeFlat = new AdVO(2, AdVO.FLAT, "Desc", Arrays.asList(1), 300, null, null, null);

        incompleteChalet = new AdVO(1, AdVO.CHALET, "Desc", Arrays.asList(1), 300, null, null, null);
        completeChalet = new AdVO(2, AdVO.CHALET, "Desc", Arrays.asList(1), 300, 300, null, null);

        incompleteGarage = new AdVO(1, AdVO.GARAGE, "", Collections.<Integer>emptyList(), 300, null, null, null);
        completeGarage = new AdVO(2, AdVO.GARAGE, "", Arrays.asList(1), 300, 300, null, null);
    }

    @Test
    void calculateIncompleteFlat() {
        assertEquals(0, fieldsRatingRuleService.calculate(incompleteFlat));
    }

    @Test
    void calculateCompleteFlat() {
        assertEquals(fieldsRatingRuleService.COMPLETE_FIELDS, fieldsRatingRuleService.calculate(completeFlat));
    }

    @Test
    void calculateIncompleteChalet() {
        assertEquals(0, fieldsRatingRuleService.calculate(incompleteChalet));
    }

    @Test
    void calculateCompleteChalet() {
        assertEquals(fieldsRatingRuleService.COMPLETE_FIELDS, fieldsRatingRuleService.calculate(completeChalet));
    }

    @Test
    void calculateIncompleteGarage(){
        assertEquals(0, fieldsRatingRuleService.calculate(incompleteGarage));
    }

    @Test
    void calculateCompleteGarage() {
        assertEquals(fieldsRatingRuleService.COMPLETE_FIELDS, fieldsRatingRuleService.calculate(completeGarage));
    }
}