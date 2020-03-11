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
public class DescriptionHighlightedWordsScoringTest {

    private Scoring scoring;

    @Before
    public void setUp() {
        scoring = new DescriptionHighlightedWordsScoring();
    }

    @Test
    public void shouldReturnNullWhenAdVOisNull() {
        //when
        AdVO out = scoring.apply(null);
        //then
        assertThat(out, is(nullValue()));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescription() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("Pisazo");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescriptionLuminoso() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("Pisazo luminoso");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescriptionNuevo() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("Pisazo nuevo");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescriptionCentrico() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("Pisazo céntrico");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescriptionReformado() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("Pisazo reformado. Muy reformado");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5 + 5));
    }

    @Test
    public void shouldReturnAdVOWithPreviousScoringWhenAdVOHasDescriptionAtico() {
        //given
        Integer initialScore = 10;
        AdVO in = MockObjectFactory.getAdVOFlat85();
        in.setDescription("ático céntrico. Muy reformado");
        in.setScore(initialScore);
        //when
        AdVO out = scoring.apply(in);
        //then
        assertThat(out, is(notNullValue()));
        assertThat(out.getScore(), is(initialScore + 5 + 5 + 5));
    }


}