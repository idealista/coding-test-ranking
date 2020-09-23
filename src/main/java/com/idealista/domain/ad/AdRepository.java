package com.idealista.domain.ad;

import java.util.List;
import java.util.Optional;

public interface AdRepository {

    void saveAd(Ad ad) ;

    Optional<Ad> findAdById(String id);

    List<Ad> getAllAds();

    List<Ad> findPublicAds();

    List<Ad> findIrrelevantAds();

}
