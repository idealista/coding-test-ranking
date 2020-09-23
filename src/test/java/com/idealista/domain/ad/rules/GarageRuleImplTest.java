package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class GarageRuleImplTest {

    private final GarageRuleImpl rule = GarageRuleImpl.INSTANCE;

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
    public void shouldApplyWhenIsGarage() {
        assertThat(rule.canApply(new Ad())).isFalse();
        assertThat(rule.canApply(flatAd)).isFalse();
        assertThat(rule.canApply(chaletAd)).isFalse();

        assertThat(rule.canApply(garageAd)).isTrue();
    }

    @Test
    public void shouldBeCompleteWhenGarageHasPictures() {
        assertThat(rule.isComplete(garageAd)).isFalse();

        garageAd.setPictures(Collections.emptyList());
        assertThat(rule.isComplete(garageAd)).isFalse();

        garageAd.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        assertThat(rule.isComplete(garageAd)).isTrue();
    }
}
