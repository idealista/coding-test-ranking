package com.idealista.application.stubs;

import com.idealista.domain.Ad;
import com.idealista.domain.AdIdentifer;
import com.idealista.domain.AdsCollection;
import com.idealista.domain.Picture;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.singletonList;

public class InMemoryCollectionStub implements AdsCollection {

    private List<Ad> adStorage = new ArrayList<Ad>(){
        {
            addAll(Arrays.asList(
                    new Ad(new AdIdentifer(1), "GARAGE", null, singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), 70, 25, null, null),
                    new Ad(new AdIdentifer(2), "CHALET", "This is a description too much long to check the score calculator", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null),
                    new Ad(new AdIdentifer(3), "FLAT", "Contains luminoso and reformado", singletonList(new Picture(1, "http://this-is-a-url.com", "HD")), null, null, null, null)
            ));
        }
    };

    @Override
    public List<Ad> getAllAdsWithScore() {
        return adStorage.stream().filter(ad -> ad.getScore() != null).collect(Collectors.toList());
    }

    @Override
    public void updateAllAds(List<Ad> updatedAds) {
        adStorage.addAll(updatedAds);
    }

    public List<Ad> getAllAds() {
        return adStorage;
    }
}
