package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class FlatRuleImplTest {

    private final FlatRuleImpl rule = FlatRuleImpl.INSTANCE;

    private static Ad flatAd;
    private static Ad chaletAd;
    private static Ad garageAd;

    @BeforeClass
    public static void beforeClass() {
        flatAd = new Ad();
        flatAd.setTypology(AdTypology.FLAT);

        chaletAd = new Ad();
        chaletAd.setTypology(AdTypology.CHALET);

        garageAd = new Ad();
        garageAd.setTypology(AdTypology.GARAGE);
    }

    @Test
    public void shouldApplyWhenIsFlat() {
        assertThat(rule.canApply(new Ad())).isFalse();
        assertThat(rule.canApply(chaletAd)).isFalse();
        assertThat(rule.canApply(garageAd)).isFalse();

        assertThat(rule.canApply(flatAd)).isTrue();
    }

    @Test
    public void shouldBeCompleteWhenFlatHasDescriptionPicturesAndHouseSize() {
        assertThat(rule.isComplete(flatAd)).isFalse();

        flatAd.setDescription("");
        flatAd.setHouseSize(0);
        flatAd.setPictures(Collections.emptyList());

        assertThat(rule.isComplete(flatAd)).isFalse();

        flatAd.setDescription(BaseCreator.randomSentence());
        flatAd.setHouseSize(BaseCreator.randomNumber());
        flatAd.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));

        assertThat(rule.isComplete(flatAd)).isTrue();
    }
}
