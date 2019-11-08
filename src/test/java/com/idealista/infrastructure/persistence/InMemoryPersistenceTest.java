package com.idealista.infrastructure.persistence;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryPersistenceTest {
    private InMemoryPersistence inMemoryPersistence;

    @BeforeEach
    void setUp() {
        List<AdVO> ads = new ArrayList<>();
        ads.add(new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 10, new Date()));
        ads.add(new AdVO(2, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 40, null));
        ads.add(new AdVO(3, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 50, null));
        ads.add(new AdVO(4, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 0, new Date()));
        ads.add(new AdVO(5, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 0, new Date()));

        List<PictureVO> pictures = new ArrayList<>();
        pictures.add((new PictureVO(1, "http://www.idealista.com/pictures/1", "SD")));
        pictures.add((new PictureVO(2, "http://www.idealista.com/pictures/2", "HD")));

        inMemoryPersistence = new InMemoryPersistence();

        inMemoryPersistence.setAds(ads);
        inMemoryPersistence.setPictures(pictures);
    }

    @Test
    void findAllAds() {
        int num = 5;
        List<AdVO> ads = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            ads.add(new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 10, new Date()));
        }

        inMemoryPersistence.setAds(ads);
        assertEquals(num, inMemoryPersistence.findAllAds().size());
    }

    @Test
    void saveNewAd() {
        AdVO ad = new AdVO(10, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        int size = inMemoryPersistence.findAllAds().size();
        inMemoryPersistence.saveAd(ad);

        assertEquals(size + 1, inMemoryPersistence.findAllAds().size());

        Optional<AdVO> optionalAd = inMemoryPersistence.findAdById(ad.getId());

        assert(optionalAd.isPresent());
        assertEquals(ad, optionalAd.get());
    }

    @Test
    void saveExistingAd() {
        AdVO ad = new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        int size = inMemoryPersistence.findAllAds().size();
        inMemoryPersistence.saveAd(ad);

        assertEquals(size, inMemoryPersistence.findAllAds().size());

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

    @Test
    void findAllAdsIrrelevantSinceIsNullOrderByScoreDesc() {
        List<AdVO> ads = inMemoryPersistence.findAllAdsIrrelevantSinceIsNullOrderByScoreDesc();

        assertEquals(2, ads.size());

        for (int i = 0; i < ads.size(); i++) {
            assert(ads.get(i).isRelevant());

            if (i < ads.size() - 1)
                assert(ads.get(i).getScore() >= ads.get(i+1).getScore());
        }
    }

    @Test
    void findAdPicturesUrlById() {
        String fakeUrl = "URL";
        int fakeId = 1001;

        List<AdVO> ads = new ArrayList<>();
        List<PictureVO> pictures = new ArrayList<>();

        ads.add(new AdVO(fakeId, "CHALET", "", Arrays.asList(100), 300, null, null, null));
        pictures.add(new PictureVO(100, fakeUrl, "HD"));

        inMemoryPersistence.setAds(ads);
        inMemoryPersistence.setPictures(pictures);

        assert(inMemoryPersistence.findAdPicturesUrlById(fakeId).contains(fakeUrl));
    }

    @Test
    void findIrrelevantAds() {
        List<AdVO> ads = inMemoryPersistence.findIrrelevantAds();

        assertEquals(3, ads.size());

        for (AdVO ad : ads) {
            assert(!ad.isRelevant());
        }
    }
}