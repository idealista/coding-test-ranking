package com.idealista.infrastructure.api.ad.search.domain;

import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.*;
import static com.idealista.infrastructure.api.ad.search.domain.AdTypology.*;

public class AdTestData {

    public static Ad.AdBuilder emptyAd(){
        return Ad.builder();
    }

    public static Ad.AdBuilder irrelevantAd(){
        return emptyAd()
            .adQuality(IRRELEVANT);
    }

    public static Ad.AdBuilder relevantAd(){
        return emptyAd()
            .adQuality(RELEVANT);
    }

    public static Ad.AdBuilder notScoredAd(){
        return emptyAd()
            .adQuality(NOT_SCORED);
    }

    public static Ad.AdBuilder flat(){
        return emptyAd()
            .typology(FLAT);
    }

    public static Ad.AdBuilder chalet(){
        return emptyAd()
            .typology(CHALET);
    }

    public static Ad.AdBuilder garage(){
        return emptyAd()
            .typology(GARAGE);
    }
}
