package com.idealista.application.service.score.rules;

import com.idealista.application.service.picture.PictureService;
import com.idealista.domain.ad.Ad;
import com.idealista.domain.picture.Picture;
import com.idealista.domain.picture.PictureQuality;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "score.rules.picture.empty-points = -10",
        "score.rules.picture.sd-points = 10",
        "score.rules.picture.hd-points = 20",
})
public class PictureRuleServiceImplTest {

    @Value("${score.rules.picture.empty-points}")
    private Integer noPicturePoints;

    @Value("${score.rules.picture.sd-points}")
    private Integer sdPicturePoints;

    @Value("${score.rules.picture.hd-points}")
    private Integer hdPicturePoints;

    private PictureRuleServiceImpl descriptionRuleService;

    private PictureService pictureService = mock(PictureService.class);

    @Before
    public void setUp() {
        descriptionRuleService = new PictureRuleServiceImpl(pictureService, noPicturePoints, sdPicturePoints, hdPicturePoints);
    }

    @Test
    public void shouldReturnMinusTen() {
        final Ad ad = new Ad();

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(-10);

        ad.setPictures(Collections.emptyList());
        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(-10);
    }

    @Test
    public void shouldReturnTen() {
        final Ad ad = new Ad();
        ad.setPictures(Collections.singletonList("1"));

        when(pictureService.getPictures(anyList()))
                .thenReturn(Collections.singletonList(new Picture(null, null, PictureQuality.SD)));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(10);
    }

    @Test
    public void shouldReturnTwenty() {
        final Ad ad = new Ad();
        ad.setPictures(Collections.singletonList("1"));

        when(pictureService.getPictures(anyList()))
                .thenReturn(Collections.singletonList(new Picture(null, null, PictureQuality.HD)));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(20);
    }

    @Test
    public void shouldReturnFifty() {
        final Ad ad = new Ad();
        ad.setPictures(Arrays.asList("1", "2", "3"));

        when(pictureService.getPictures(anyList()))
                .thenReturn(Arrays.asList(
                        new Picture(null, null, PictureQuality.HD),
                        new Picture(null, null, PictureQuality.HD),
                        new Picture(null, null, PictureQuality.SD)));

        assertThat(descriptionRuleService.calculateScore(ad)).isEqualTo(50);
    }
}
