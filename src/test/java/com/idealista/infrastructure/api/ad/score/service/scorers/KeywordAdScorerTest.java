package com.idealista.infrastructure.api.ad.score.service.scorers;

import com.idealista.infrastructure.api.ad.score.configuration.KeywordScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.KeywordAdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.emptyAd;
import static org.assertj.core.api.Assertions.assertThat;

public class KeywordAdScorerTest {

    private KeywordScoreConfiguration keywordScoreConfiguration;

    private AdScorer keywordAdScorer;

    @BeforeEach
    public void setup(){
        this.keywordScoreConfiguration = new KeywordScoreConfiguration();
        this.keywordAdScorer = new KeywordAdScorer(
            keywordScoreConfiguration
        );
    }

    @Test
    public void givenTextWithoutKeywords_whenGetScore_expectNoPoints(){
        //given
        List<String> keywords = Arrays.asList("keyword", "anotherKeyword");
        Integer score = 5;
        Integer expectedScore = 0;
        keywordScoreConfiguration.setKeywords(keywords);
        keywordScoreConfiguration.setScore(score);

        Ad adWithNoKeywords = emptyAd().description(Optional.of(LoremIpsum.getInstance().getWords(5))).build();

        //when
        Integer actualScore = keywordAdScorer.getScore(adWithNoKeywords);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenTextWithKeywords_whenGetScore_expectPoints(){
        //given
        List<String> keywords = Arrays.asList("keyword", "anotherKeyword", "yetAnotherKeyword");
        Integer score = 5;
        Integer expectedScore = 15;
        keywordScoreConfiguration.setKeywords(keywords);
        keywordScoreConfiguration.normalizeKeywords();
        keywordScoreConfiguration.setScore(score);

        Ad adWithKeywords = emptyAd().description(Optional.of("keyword anotherKeyword yetAnotherKeyword")).build();

        //when
        Integer actualScore = keywordAdScorer.getScore(adWithKeywords);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

}
