package com.idealista.application.service.impl;

import com.idealista.application.service.rating.RatingRuleService;
import com.idealista.application.service.rating.impl.DescriptionRatingRuleService;
import com.idealista.application.service.rating.impl.FieldsRatingRuleService;
import com.idealista.application.service.rating.impl.PictureRatingRuleService;
import com.idealista.application.service.rating.impl.SpecialWordsRatingRuleService;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class AdRatingServiceImplTest {
    private AdRatingServiceImpl adRatingService;

    @BeforeEach
    void setUp() {
        InMemoryPersistence inMemoryPersistence = new InMemoryPersistence();

        adRatingService = new AdRatingServiceImpl(
                inMemoryPersistence,
                createRuleSet(inMemoryPersistence));
    }

    @Test
    void relevantAdToIrrelevant() {
        AdVO relevantAd = new AdVO(1, "", "", Arrays.asList(1), 0, 0, null, null);
        AdVO ratedAd = adRatingService.rateAd(relevantAd);

        assertFalse(ratedAd.isRelevant());
    }

    @Test
    void irrelevantAdToIrrelevantDoesNotChangeDate() {
        Date oldDate = new Date(Instant.parse("1994-07-05T00:00:00.00Z").toEpochMilli());
        AdVO irrelevantAd = new AdVO(1, "", "", Arrays.asList(1), 0, 0, 0, oldDate);
        AdVO ratedAd = adRatingService.rateAd(irrelevantAd);

        assertEquals(oldDate, ratedAd.getIrrelevantSince());
    }

    @Test
    void irrelevantAdToRelevant() {
        AdVO irrelevantAd = new AdVO(1, "FLAT", "ático céntrico luminoso", Arrays.asList(1), 300, 0, 0, new Date());
        AdVO ratedAd = adRatingService.rateAd(irrelevantAd);

        assertTrue(ratedAd.isRelevant());
    }

    private Set<RatingRuleService> createRuleSet(InMemoryPersistence inMemoryPersistence) {
        Set<RatingRuleService> rules = new HashSet<>();

        rules.add(new PictureRatingRuleService(inMemoryPersistence));
        rules.add(new FieldsRatingRuleService());
        rules.add(new DescriptionRatingRuleService());
        rules.add(new SpecialWordsRatingRuleService());

        return rules;
    }
}