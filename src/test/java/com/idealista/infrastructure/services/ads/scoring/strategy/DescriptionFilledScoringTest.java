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
public class DescriptionFilledScoringTest {

    private Scoring scoring;

    @Before
    public void setUp() {
        scoring = new DescriptionFilledScoring();
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
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription(null);
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithScoringCalculatedWhenAdVOHasNullScore() {
        //given
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(null);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(5));
    }

    @Test
    public void shouldReturnAdVOWithScoringAddedWhenAdVOHasPreviousScore() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5));
    }

}