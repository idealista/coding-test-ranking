package com.idealista.infrastructure;

import com.idealista.infrastructure.config.RepositoryTest;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
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
        assertEquals(20, updatedAd.getScore());
    }

    @Test
    void shouldRetrievedAPictureById() {
        //when
        final PictureVO storedPicture = inMemoryPersistence.findPictureById(1);

        //then
        assertNotNull(storedPicture);
        assertEquals(1, storedPicture.getId());
    }
}
