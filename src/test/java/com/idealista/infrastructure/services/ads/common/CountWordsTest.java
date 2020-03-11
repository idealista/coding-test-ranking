package com.idealista.infrastructure.services.ads.common;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
public class CountWordsTest {

    @Test
    public void shouldReturnZeroWhenCadIsEmpty() {
        //given
        CountWords countWords = new CountWords(EMPTY);
        //when
        int out = countWords.count();
        //then
        assertEquals(out, 0);
    }

    @Test
    public void shouldReturnCountWhenCadIsValid1() {
        //given
        CountWords countWords = new CountWords("Hello world!!");
        //when
        int out = countWords.count();
        //then
        assertEquals(out, 2);
    }

    @Test
    public void shouldReturnCountWhenCadIsValid2() {
        //given
        CountWords countWords = new CountWords("Hello--world!!");
        //when
        int out = countWords.count();
        //then
        assertEquals(out, 2);
    }

}