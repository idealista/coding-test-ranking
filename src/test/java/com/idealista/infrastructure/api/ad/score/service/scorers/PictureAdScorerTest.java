package com.idealista.infrastructure.api.ad.score.service.scorers;

import com.idealista.infrastructure.api.ad.score.configuration.PictureAdScoreConfiguration;
import com.idealista.infrastructure.api.ad.score.service.scorers.AdScorer;
import com.idealista.infrastructure.api.ad.score.service.scorers.impl.PictureAdScorer;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdPicture;
import com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality;
import com.idealista.infrastructure.api.ad.search.domain.AdTestData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality.HD;
import static com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality.SD;
import static com.idealista.infrastructure.api.ad.search.domain.AdPictureTestData.hdAdPicture;
import static com.idealista.infrastructure.api.ad.search.domain.AdPictureTestData.sdAdPicture;
import static com.idealista.infrastructure.api.ad.search.domain.AdTestData.emptyAd;
import static org.assertj.core.api.Assertions.assertThat;


public class PictureAdScorerTest {

    private PictureAdScoreConfiguration pictureAdScoreConfiguration;

    private AdScorer pictureAdScorer;

    @BeforeEach
    public void setup(){
        this.pictureAdScoreConfiguration = new PictureAdScoreConfiguration();
        this.pictureAdScorer = new PictureAdScorer(
            pictureAdScoreConfiguration
        );
    }

    @Test
    public void givenNoAdPictures_whenGetScore_expectPenalty(){
        //given
        Ad adWithNoPictures = emptyAd().build();
        Integer noPicturePenalty = -10;

        pictureAdScoreConfiguration.setNoPicturePenalty(noPicturePenalty);

        //when
        Integer actualScore = pictureAdScorer.getScore(adWithNoPictures);

        //then
        assertThat(actualScore).isEqualTo(noPicturePenalty);
    }

    @Test
    public void givenHDAndSDAdPictures_whenGetScore_expectScore(){
        //given
        AdPicture hdPicture = hdAdPicture().build();
        AdPicture sdPicture = sdAdPicture().build();
        Ad adWithPictures = emptyAd().adPictures(Arrays.asList(hdPicture, sdPicture)).build();
        Integer hdScore = 20;
        Integer sdScore = 10;
        Integer expectedScore = hdScore + sdScore;

        Map<AdPictureQuality, Integer> adPictureQualityScores = new HashMap<AdPictureQuality, Integer>(){{
            put(HD, hdScore);
            put(SD, sdScore);
        }};

        pictureAdScoreConfiguration.setPictureQualityScore(adPictureQualityScores);

        //when
        Integer actualScore = pictureAdScorer.getScore(adWithPictures);

        //then
        assertThat(actualScore).isEqualTo(expectedScore);
    }

}
