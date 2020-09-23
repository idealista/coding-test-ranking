package com.idealista.application.service.score.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "score.rules.description-typology.flat.min-points = 10",
        "score.rules.description-typology.flat.min-words = 20",
        "score.rules.description-typology.flat.full-points = 30",
        "score.rules.description-typology.flat.full-words = 50",
        "score.rules.description-typology.chalet.full-points = 20",
        "score.rules.description-typology.chalet.full-words = 50",
})
public class DescriptionTypologyRuleServiceImplTest {

    @Value("${score.rules.description-typology.flat.min-points}")
    private Integer flatMinPoints;
    @Value("${score.rules.description-typology.flat.min-words}")
    private Integer flatMinWords;
    @Value("${score.rules.description-typology.flat.full-points}")
    private Integer flatFullPoints;
    @Value("${score.rules.description-typology.flat.full-words}")
    private Integer flatFullWords;
    @Value("${score.rules.description-typology.chalet.full-points}")
    private Integer chaletFullPoints;
    @Value("${score.rules.description-typology.chalet.full-words}")
    private Integer chaletFullWords;

    private DescriptionTypologyRuleServiceImpl descriptionRuleService;

    @Before
    public void setUp() {
        descriptionRuleService = new DescriptionTypologyRuleServiceImpl(flatMinPoints, flatMinWords, flatFullPoints, flatFullWords, chaletFullPoints, chaletFullWords);
    }

    @Test
    public void shouldReturnZero() {
        final Ad ad = new Ad();

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnZeroWhenFlatDescriptionDontHaveEnoughWords() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.FLAT);
        ad.setDescription(BaseCreator.faker().lorem().sentence(flatMinWords - 1, 0));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnTenWhenFlatDescriptionHaveMoreThanTwentyWords() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.FLAT);

        ad.setDescription(BaseCreator.faker().lorem().sentence(flatMinWords, 0));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(flatMinPoints);

        ad.setDescription(BaseCreator.faker().lorem().sentence(flatFullWords - 1, 0));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(flatMinPoints);
    }

    @Test
    public void shouldReturnThirtyWhenFlatDescriptionHaveEnoughWords() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.FLAT);

        ad.setDescription(BaseCreator.faker().lorem().sentence(flatFullWords, 0));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(flatFullPoints);

        ad.setDescription(BaseCreator.faker().lorem().sentence(flatFullWords, 10));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(flatFullPoints);
    }




    @Test
    public void shouldReturnZeroWhenChaletDescriptionDontHaveEnoughWords() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.CHALET);
        ad.setDescription(BaseCreator.faker().lorem().sentence(chaletFullWords - 1, 0));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnTwentyWhenChaletDescriptionHaveEnoughWords() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.CHALET);

        ad.setDescription(BaseCreator.faker().lorem().sentence(chaletFullWords, 0));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(chaletFullPoints);

        ad.setDescription(BaseCreator.faker().lorem().sentence(chaletFullWords, 10));
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(chaletFullPoints);
    }

}
