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

        assertEquals(
                ads,
                inMemoryPersistence.findAllAds(),
                "Returned list is not the same as the one in the repository"
        );
    }

    @Test
    void saveNewAd() {
        AdVO ad = new AdVO(10, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        int size = inMemoryPersistence.findAllAds().size();
        inMemoryPersistence.saveAd(ad);

        assertEquals(size + 1, inMemoryPersistence.findAllAds().size(), "The size of the list didn't increment by one");

        Optional<AdVO> optionalAd = inMemoryPersistence.findAdById(ad.getId());

        assertTrue(optionalAd.isPresent(), "Did not find the inserted ad");
        assertEquals(ad, optionalAd.get(), "Inserted ad is not the same as the returned one");
    }

    @Test
    void saveExistingAd() {
        AdVO ad = new AdVO(1, "CHALET", "", Collections.<Integer>emptyList(), 300, null, null, null);

        int size = inMemoryPersistence.findAllAds().size();
        inMemoryPersistence.saveAd(ad);

        assertEquals(size, inMemoryPersistence.findAllAds().size(), "The size of the list changed");

        Optional<AdVO> optionalAd = inMemoryPersistence.findAdById(ad.getId());

        assertTrue(optionalAd.isPresent(), "Inserted ad is not present");
        assertEquals(ad, optionalAd.get(), "Found ad is not the same");
    }

    @Test
    void findExistingPictureById() {
        Optional<PictureVO> pictureOptional = inMemoryPersistence.findPictureById(1);

        assertTrue(pictureOptional.isPresent(), "Did not find picture with id 1");
    }

    @Test
    void findNotExistingPictureById() {
        Optional<PictureVO> pictureOptional = inMemoryPersistence.findPictureById(-1);

        assertTrue(!pictureOptional.isPresent(), "Found a picture with fake id -1");
    }

    @Test
    void findExistingAdById() {
        Optional<AdVO> adOptional = inMemoryPersistence.findAdById(1);

        assertTrue(adOptional.isPresent(), "Did not find the ad with id 1");
    }

    @Test
    void findNotExistingAdById() {
        Optional<AdVO> adOptional = inMemoryPersistence.findAdById(-1);

        assertTrue(!adOptional.isPresent(), "Found an ad with the fake id -1");
    }

    @Test
    void findAllAdsIrrelevantSinceIsNullOrderByScoreDesc() {assertEquals(
        Arrays.asList(
                new AdVO(3, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 50, null),
                new AdVO(2, "CHALET", "", Collections.<Integer>emptyList(), 300, null, 40, null)
            ),
            inMemoryPersistence.findAllAdsIrrelevantSinceIsNullOrderByScoreDesc(),
            "The expected list wasn't returned"
        );
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

        assertTrue(inMemoryPersistence.findAdPicturesUrlById(fakeId).contains(fakeUrl), "Picture url is not present");
    }

    @Test
    void findIrrelevantAds() {
        List<AdVO> ads = inMemoryPersistence.findIrrelevantAds();

        assertEquals(3, ads.size());

        for (AdVO ad : ads) {
            assertTrue(!ad.isRelevant(), "Ad is relevant");
        }
    }
}