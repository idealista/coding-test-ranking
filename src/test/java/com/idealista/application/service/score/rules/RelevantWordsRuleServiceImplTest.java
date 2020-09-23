package com.idealista.application.service.score.rules;

import com.idealista.domain.ad.Ad;
import com.idealista.util.BaseCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "score.rules.relevant-words.points = 5",
        "score.rules.relevant-words.words = luminoso, nuevo, céntrico, reformado, ático",
})
public class RelevantWordsRuleServiceImplTest {

    @Value("${score.rules.relevant-words.points}")
    private Integer points;

    @Value("#{'${score.rules.relevant-words.words}'.split(',')}")
    private Set<String> words;

    private RelevantWordsRuleServiceImpl descriptionRuleService;

    @Before
    public void setUp() {
        descriptionRuleService = new RelevantWordsRuleServiceImpl(points, words);
    }

    @Test
    public void shouldReturnZero() {
        final Ad ad = new Ad();

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);

        ad.setDescription(BaseCreator.randomSentence());
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(0);
    }

    @Test
    public void shouldReturnFive() {
        final Ad ad = new Ad();
        ad.setDescription(BaseCreator.randomSentence() + " Centrico");

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(5);
    }

    @Test
    public void shouldReturnTen() {
        final Ad ad = new Ad();
        ad.setDescription(BaseCreator.randomSentence() + " luminoso " + BaseCreator.randomSentence() + " reformado");

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(10);
    }

    @Test
    public void shouldReturnTwenty() {
        final Ad ad = new Ad();
        ad.setDescription(BaseCreator.randomSentence() + " luminoso " + BaseCreator.randomSentence() + " reformado, NUEVO ATICO!!");

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(20);
    }

}
