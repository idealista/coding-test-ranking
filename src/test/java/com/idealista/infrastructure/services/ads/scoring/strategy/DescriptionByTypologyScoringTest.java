package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.entities.AdVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class DescriptionByTypologyScoringTest {

    private Scoring scoring;

    @Before
    public void setUp() {
        scoring = new DescriptionByTypologyScoring();
    }

    @Test
    public void shouldReturnNullWhenAdVOisNull() {
        //when
        AdVO out = scoring.apply(null);
        //then
        assertThat(out, is(nullValue()));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasEmptyDescription() {
        //given
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription(null);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(in.getScore()));
    }

    @Test
    public void shouldReturnAdVOWithSameScoringWhenAdVONotChaletFlat() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOGarage5();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithSameScoringWhenAdVOChaletShortDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOChaletLongDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        in.setDescription(in.getDescription()+ " " + in.getDescription()+ " " + in.getDescription());
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 20));
    }

    @Test
    public void shouldReturnAdVOWithSameScoringWhenAdVOFlatShortDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(initialScore);
        in.setDescription("Pisazo");
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithSameScoringWhenAdVOFlatMiddleDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 10));
    }

    @Test
    public void shouldReturnAdVOWithSameScoringWhenAdVOFlatLongDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(initialScore);
        in.setDescription(in.getDescription() + " " + in.getDescription() + " " + in.getDescription());
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 30));
    }

}