package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.MockObjectFactory;
import com.idealista.infrastructure.controllers.PublicAd;
import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.entities.PictureVO;
import com.idealista.infrastructure.services.ads.converters.AdVOToPublicAdConverter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class CompleteDataScoringTest {

    private Scoring scoring;

    @Before
    public void setUp() {
        scoring = new CompleteDataScoring();
    }

    @Test
    public void shouldReturnNullWhenAdVOisNull() {
        //when
        AdVO out = scoring.apply(null);
        //then
        assertThat(out, is(nullValue()));
    }

    @Test
    public void shouldReturnAdVOWithDefaultScoringWhenAdVOGarageIncomplete() {
        //given
        AdVO in = MockObjectFactory.getAdVOGarage5();
        in.setScore(null);
        in.setPictures(null);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(0));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOGarageComplete() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOGarage5();
        in.setScore(initialScore);
        in.setPictures(Collections.singletonList(
                new PictureVO(3, "http://www.idealista.com/pictures/3", "SD")));
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 40));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOFlatIncomplete() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(initialScore);
        in.setPictures(null);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOFlatComplete() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 40));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOChaletIncomplete() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        in.setDescription(null);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVOChaletComplete() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 40));
    }

    @Test
    public void shouldReturnAdVOWithCalculatedScoringWhenAdVONotGarageFlatChalet() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOChalet65();
        in.setScore(initialScore);
        in.setTypology("LOCAL");
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 40));
    }

}