package com.idealista.application.service.score.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.util.BaseCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "score.rules.description.points = 5")
public class DescriptionRuleServiceImplTest {

    @Value("${score.rules.description.points}")
    private Integer points;

    private DescriptionRuleServiceImpl descriptionRuleService;

    @Before
    public void setUp() {
        descriptionRuleService = new DescriptionRuleServiceImpl(points);
    }

    @Test
    public void shouldReturnZero() {
        final Ad ad = new Ad();

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnFive() {
        final Ad ad = new Ad();
        ad.setDescription(BaseCreator.randomSentence());

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(5);
    }
}
