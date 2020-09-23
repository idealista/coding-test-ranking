package com.idealista.infrastructure.persistence;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdCreator;
import com.idealista.util.BaseCreator;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class InMemoryAdPersistenceTest {

    private static InMemoryAdPersistence inMemoryAdPersistence;

    @BeforeClass
    public static void beforeClass() {
        inMemoryAdPersistence = new InMemoryAdPersistence();
    }

    @Test
    public void shouldGetAllAds() {
        final List<Ad> allAds = inMemoryAdPersistence.getAllAds();

        assertThat(allAds).isNotEmpty();
    }

    @Test
    public void shouldSaveAd() {
        Ad ad = AdCreator.create();

        assertThatCode(() -> inMemoryAdPersistence.saveAd(ad)).doesNotThrowAnyException();
    }

    @Test
    public void shouldFindAdById() {
        Ad ad = AdCreator.create();

        inMemoryAdPersistence.saveAd(ad);

        assertThat(inMemoryAdPersistence.findAdById(ad.getId())).contains(ad);
    }

    @Test
    public void shouldFindAdByIdReturnOptionalEmpty() {
        assertThat(inMemoryAdPersistence.findAdById(BaseCreator.randomUuid())).isEmpty();
    }

    @Test
    public void shouldFindPublicAds() {
        assertThat(inMemoryAdPersistence.findPublicAds()).isEmpty();
    }

    @Test
    public void shouldFindIrrelevantAds() {
        final List<Ad> allAds = inMemoryAdPersistence.getAllAds();

        assertThat(inMemoryAdPersistence.findIrrelevantAds()).containsAll(allAds);
    }
}
