package com.idealista.infrastructure.api.mapper;

import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MapperTest {
    Mapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new Mapper();
    }

    @Test
    void mapPublicAd() {
        AdVO adVO = new AdVO(1, "FLAT", "Description", Arrays.asList(1), 100, 100, 100, null);
        List<String> urlList = Arrays.asList("URL1", "URL2");
        PublicAd expectedPublicAd = new PublicAd(1, "FLAT", "Description", urlList, 100, 100);

        assertEquals(expectedPublicAd, Mapper.mapPublicAd(adVO, urlList));
    }

    @Test
    void mapQualityAd() {
        AdVO adVO = new AdVO(1, "FLAT", "Description", Arrays.asList(1), 100, 100, 100, null);
        List<String> urlList = Arrays.asList("URL1", "URL2");
        QualityAd expectedQualityAd = new QualityAd(1, "FLAT", "Description", urlList, 100, 100, 100, null);

        assertEquals(expectedQualityAd, Mapper.mapQualityAd(adVO, urlList));
    }
}