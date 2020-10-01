package com.idealista.infrastructure;

import com.idealista.infrastructure.config.RepositoryTest;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
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
}
