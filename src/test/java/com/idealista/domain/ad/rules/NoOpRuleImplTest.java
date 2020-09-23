package com.idealista.domain.ad.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NoOpRuleImplTest {

    private final NoOpRuleImpl rule = NoOpRuleImpl.INSTANCE;

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
    public void shouldApplyAlways() {
        assertThat(rule.canApply(null)).isTrue();
        assertThat(rule.canApply(new Ad())).isTrue();
        assertThat(rule.canApply(flatAd)).isTrue();
        assertThat(rule.canApply(chaletAd)).isTrue();
        assertThat(rule.canApply(garageAd)).isTrue();
    }

    @Test
    public void shouldBeCompleteAlways() {
        assertThat(rule.isComplete(null)).isTrue();
        assertThat(rule.isComplete(new Ad())).isTrue();
        assertThat(rule.isComplete(flatAd)).isTrue();
        assertThat(rule.isComplete(chaletAd)).isTrue();
        assertThat(rule.isComplete(garageAd)).isTrue();
    }
}
