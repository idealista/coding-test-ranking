package com.idealista.application.service.rating.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ImageRatingRuleServiceTest {
    private ImageRatingRuleService imageRatingRuleService;
    private InMemoryPersistence inMemoryPersistence;

    @BeforeEach
    void setUp() {
        List<PictureVO> pictures = new ArrayList<>();
        pictures.add((new PictureVO(1, "http://www.idealista.com/pictures/1", "SD")));
        pictures.add((new PictureVO(2, "http://www.idealista.com/pictures/2", "SD")));
        pictures.add((new PictureVO(3, "http://www.idealista.com/pictures/3", "HD")));
        pictures.add((new PictureVO(4, "http://www.idealista.com/pictures/4", "HD")));

        inMemoryPersistence = new InMemoryPersistence();
        inMemoryPersistence.setPictures(pictures);

        imageRatingRuleService = new ImageRatingRuleService(inMemoryPersistence);
    }

    @Test
    void calculateEmptyList() {
        AdVO ad = new AdVO(0, "GARAGE", "", Collections.<Integer>emptyList(), 300, null, null, null);

        assertEquals(imageRatingRuleService.NO_PICTURE, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateNullList() {
        AdVO ad = new AdVO(0, "GARAGE", "", null, 300, null, null, null);

        assertEquals(imageRatingRuleService.NO_PICTURE, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateImagesNotInRepository() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(-1), 300, null, null, null);

        assertEquals(imageRatingRuleService.NO_PICTURE, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateImageHD() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(3), 300, null, null, null);

        assertEquals(imageRatingRuleService.HD_PICTURE, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMultipleImagesHD() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(3, 4), 300, null, null, null);

        assertEquals(imageRatingRuleService.HD_PICTURE * 2, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateImageSD() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(1), 300, null, null, null);

        assertEquals(imageRatingRuleService.SD_PICTURE, imageRatingRuleService.calculate(ad));
    }

    @Test
    void calculateMultipleImagesSD() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(1, 2), 300, null, null, null);

        assertEquals(imageRatingRuleService.SD_PICTURE * 2, imageRatingRuleService.calculate(ad));
    }

    void calculateMixedImages() {
        AdVO ad = new AdVO(0, "GARAGE", "", Arrays.asList(-1, 1, 3), 300, null, null, null);

        assertEquals(imageRatingRuleService.SD_PICTURE + imageRatingRuleService.HD_PICTURE, imageRatingRuleService.calculate(ad));
    }
}