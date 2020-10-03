package com.idealista.infrastructure.persistence;

import com.idealista.infrastructure.persistence.config.RepositoryTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@RepositoryTest
@ActiveProfiles("in-memory-repository")
public class InMemoryAdRepositoryIT {

    @Autowired
    private InMemoryPersistence inMemoryPersistence;

    @Test
    void shouldRetrieveAllTheSavedAds() {
        //when
        final List<AdVO> allAds = inMemoryPersistence.findAllAds();

        //then
        assertFalse(allAds.isEmpty());
        assertEquals(8, allAds.size());
    }

    @Test
    void shouldUpdateAListOfAds() {
        //given
        final AdVO adWithScore = new AdVO(1, "CHALET", "Este piso es una ganga, compra, compra, COMPRA!!!!!", emptyList(), 300, null, 20, null);

        //when
        inMemoryPersistence.updateAd(adWithScore);

        //then
        final AdVO updatedAd = inMemoryPersistence.findById(1);
        assertNotNull(updatedAd);
        assertEquals(Integer.valueOf(20), updatedAd.getScore());
    }

    @Test
    void shouldRetrievedAPictureById() {
        //when
        final PictureVO storedPicture = inMemoryPersistence.findPictureById(1);

        //then
        assertNotNull(storedPicture);
        assertEquals(Integer.valueOf(1), storedPicture.getId());
    }
}
