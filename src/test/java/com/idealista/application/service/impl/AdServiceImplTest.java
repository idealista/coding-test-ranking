package com.idealista.application.service.impl;

import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdServiceImplTest {
    private PictureVO picture;
    private InMemoryPersistence inMemoryPersistence;
    private AdServiceImpl adService;
    private String fakeUrl = "URL";

    private List<AdVO> allAds;
    private List<AdVO> irrlevantAds;
    private List<AdVO> orderedRelevantAds;

    @BeforeEach
    void setUp() {
        picture = new PictureVO(1, fakeUrl, "");

        allAds = Arrays.asList(
                new AdVO(1, "", "", Arrays.asList(1), 0, 0, 0, new Date()),
                new AdVO(2, "", "", null, 0, 0, 100, null),
                new AdVO(3, "", "", null, 0, 0, 100, null));

        irrlevantAds = Arrays.asList(new AdVO(1, "", "", Arrays.asList(1), 0, 0, 0, new Date()));

        orderedRelevantAds = Arrays.asList(
                new AdVO(2, "", "", null, 0, 0, 100, null),
                new AdVO(3, "", "", null, 0, 0, 100, null));

        inMemoryPersistence = new InMemoryPersistence();

        inMemoryPersistence.setPictures(Arrays.asList(picture));
        inMemoryPersistence.setAds(allAds);

        adService = new AdServiceImpl(inMemoryPersistence);
    }

    @Test
    void getOrderedRelevantAds() {
        List<AdVO> result = adService.getOrderedRelevantAds();

        assertEquals(result, orderedRelevantAds);
    }

    @Test
    void getAdPictureUrls() {
        List<String> result = adService.getAdPictureUrls(1);

        assertEquals(Arrays.asList(fakeUrl), result);
    }

    @Test
    void getAll() {
        assertEquals(allAds, adService.getAll());
    }

    @Test
    void getIrrelevantAds() {
        assertEquals(irrlevantAds, adService.getIrrelevantAds());
    }
}