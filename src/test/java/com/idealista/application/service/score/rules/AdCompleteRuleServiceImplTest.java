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
@TestPropertySource(properties = "score.rules.completed.points = 5")
public class AdCompleteRuleServiceImplTest {

    @Value("${score.rules.completed.points}")
    private Integer points;

    private AdCompleteRuleServiceImpl descriptionRuleService;

    @Before
    public void setUp() {
        descriptionRuleService = new AdCompleteRuleServiceImpl(points);
    }

    @Test
    public void shouldReturnZero() {
        final Ad ad = new Ad();

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnFiveWhenFlatIsComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.FLAT);
        ad.setDescription(BaseCreator.randomSentence());
        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        ad.setHouseSize(BaseCreator.randomNumber());

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(5);
    }

    @Test
    public void shouldReturnFiveWhenChaletIsComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.CHALET);
        ad.setDescription(BaseCreator.randomSentence());
        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        ad.setHouseSize(BaseCreator.randomNumber());
        ad.setGardenSize(BaseCreator.randomNumber());

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(5);
    }

    @Test
    public void shouldReturnFiveWhenGarageIsComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.GARAGE);
        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(5);
    }

}
