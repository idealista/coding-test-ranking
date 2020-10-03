package com.idealista.infrastructure.properties;

import com.idealista.domain.ExtractScoreValues;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class YmlExtractScoreValuesIT {

    @Autowired
    private ExtractScoreValues extractScoreValues;

    @Test
    void shouldExtractCompleteAdScoreValuesFromPropertyFile() {
        //when
        final int completeAdScore = extractScoreValues.getCompleteAdScore();

        //then
        assertEquals(40, completeAdScore);
    }

    @Test
    void shouldExtractSpecialWordScoreValuesFromPropertyFile() {
        //when
        final int specialWordScore = extractScoreValues.getSpecialWordScore();

        //then
        assertEquals(5, specialWordScore);
    }

    @Test
    void shouldExtractHDPictureScoreValuesFromPropertyFile() {
        //when
        final int hdPictureScore = extractScoreValues.getHDPictureScore();

        //then
        assertEquals(20, hdPictureScore);
    }

    @Test
    void shouldExtractSDPictureScoreValuesFromPropertyFile() {
        //when
        final int sdPictureScore = extractScoreValues.getSDPictureScore();

        //then
        assertEquals(10, sdPictureScore);
    }

    @Test
    void shouldExtractNotPictureScoreValuesFromPropertyFile() {
        //when
        final int notPictureScore = extractScoreValues.getNotPictureScore();

        //then
        assertEquals(-10, notPictureScore);
    }
}
