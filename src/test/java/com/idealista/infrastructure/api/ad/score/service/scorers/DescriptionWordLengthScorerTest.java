package com.idealista.infrastructure.api.ad.score.service.scorers;

import com.idealista.infrastructure.api.ad.score.configuration.WordRangeByTypologyConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.DescriptionWordLengthAdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.WordRange;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdTypology;
import com.thedeanda.lorem.LoremIpsum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.flat;
import static com.idealista.infrastructure.api.ad.search.domain.AdTypology.FLAT;
import static org.assertj.core.api.Assertions.assertThat;

public class DescriptionWordLengthScorerTest {

    private WordRangeByTypologyConfiguration wordRangeByTypologyConfiguration;

    private AdScorer descriptionWordLengthAdScorer;

    @BeforeEach
    public void setup(){
        this.wordRangeByTypologyConfiguration = new WordRangeByTypologyConfiguration();
        this.descriptionWordLengthAdScorer = new DescriptionWordLengthAdScorer(
            wordRangeByTypologyConfiguration
        );
    }

    @Test
    public void givenWordsOutOfRanges_whenGetScore_expectNoPoints(){
        //when
        String adDescriptionWithFewWords = LoremIpsum.getInstance().getWords(1);
        Ad adWithFewWords = flat().description(Optional.of(adDescriptionWithFewWords)).build();
        WordRange wordRange = new WordRange(20, 40, 10);
        Map<AdTypology, List<WordRange>> wordRangeByTypology = new HashMap<AdTypology, List<WordRange>>() {{
            put(FLAT, Arrays.asList(wordRange));
        }};
        wordRangeByTypologyConfiguration.setWordRanges(wordRangeByTypology);

        Integer expectedScore = 0;

        //when
        Integer actualScore = descriptionWordLengthAdScorer.getScore(adWithFewWords);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

    @Test
    public void givenWordsInRanges_whenGetScore_expectPoints(){
        //when
        String adDescriptionWithFewWords = LoremIpsum.getInstance().getWords(30);
        Ad adWithFewWords = flat().description(Optional.of(adDescriptionWithFewWords)).build();
        Integer score = 10;
        WordRange wordRange = new WordRange(20, 40, score);
        Map<AdTypology, List<WordRange>> wordRangeByTypology = new HashMap<AdTypology, List<WordRange>>() {{
            put(FLAT, Arrays.asList(wordRange));
        }};
        wordRangeByTypologyConfiguration.setWordRanges(wordRangeByTypology);

        //when
        Integer actualScore = descriptionWordLengthAdScorer.getScore(adWithFewWords);

        //then
        assertThat(actualScore).isEqualTo(score);
    }
}
