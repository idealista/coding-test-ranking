package com.idealista.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPersistenceTest {
    private InMemoryPersistence inMemoryPersistence;

    @BeforeEach
    void setUp() {
        List<AdVO> ads = new ArrayList<>();
        ads.add(new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null));
        ads.add(new AdVO(2, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null));

        List<PictureVO> pictures = new ArrayList<>();
        pictures.add((new PictureVO(1, "http://www.idealista.com/pictures/1", "SD")));
        pictures.add((new PictureVO(2, "http://www.idealista.com/pictures/2", "HD")));

        inMemoryPersistence = new InMemoryPersistence();

        inMemoryPersistence.setAds(ads);
        inMemoryPersistence.setPictures(pictures);
    }

    @Test
    void findAllAds() {
        assertEquals(2, inMemoryPersistence.findAllAds().size());
    }

    @Test
    void saveNewAd() {
        AdVO ad = new AdVO(10, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        inMemoryPersistence.saveAd(ad);

        assertEquals(3, inMemoryPersistence.findAllAds().size());

        Optional<AdVO> optionalAd = inMemoryPersistence.findAdById(ad.getId());

        assert(optionalAd.isPresent());
        assertEquals(ad, optionalAd.get());
    }

    @Test
    void saveExistingAd() {
        AdVO ad = new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        inMemoryPersistence.saveAd(ad);

        assertEquals(2, inMemoryPersistence.findAllAds().size());

        Optional<AdVO> optionalAd = inMemoryPersistence.findAdById(ad.getId());

        assert(optionalAd.isPresent());
        assertEquals(ad, optionalAd.get());
    }

    @Test
    void findExistingPictureById() {
        Optional<PictureVO> pictureOptional = inMemoryPersistence.findPictureById(1);

        assert(pictureOptional.isPresent());
    }

    @Test
    void findNotExistingPictureById() {
        Optional<PictureVO> pictureOptional = inMemoryPersistence.findPictureById(-1);

        assert(!pictureOptional.isPresent());
    }

    @Test
    void findExistingAdById() {
        Optional<AdVO> adOptional = inMemoryPersistence.findAdById(1);

        assert(adOptional.isPresent());
    }

    @Test
    void findNotExistingAdById() {
        Optional<AdVO> adOptional = inMemoryPersistence.findAdById(-1);

        assert(!adOptional.isPresent());
    }
}