package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ChaletRuleImplTest {

    private final ChaletRuleImpl rule = ChaletRuleImpl.INSTANCE;

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
    public void shouldApplyWhenIsChalet() {
        assertThat(rule.canApply(new Ad())).isFalse();
        assertThat(rule.canApply(flatAd)).isFalse();
        assertThat(rule.canApply(garageAd)).isFalse();

        assertThat(rule.canApply(chaletAd)).isTrue();
    }

    @Test
    public void shouldBeCompleteWhenChaletHasDescriptionPicturesHouseSizeAndGardenSize() {
        assertThat(rule.isComplete(chaletAd)).isFalse();

        chaletAd.setDescription("");
        chaletAd.setHouseSize(0);
        chaletAd.setGardenSize(0);
        chaletAd.setPictures(Collections.emptyList());

        assertThat(rule.isComplete(chaletAd)).isFalse();

        chaletAd.setDescription(BaseCreator.randomSentence());
        chaletAd.setHouseSize(BaseCreator.randomNumber());
        chaletAd.setGardenSize(BaseCreator.randomNumber());
        chaletAd.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));

        assertThat(rule.isComplete(chaletAd)).isTrue();
    }
}
