package com.idealista.application.service.publicAd;

import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;

import java.util.List;

public final class PublicAdResponseCreator extends BaseCreator {

    public static PublicAdResponse create(String id, String typology, String description, List<String> pictureUrls, Integer houseSize, Integer gardenSize) {
        final PublicAdResponse publicAdResponse = new PublicAdResponse();
        publicAdResponse.setId(id);
        publicAdResponse.setTypology(typology);
        publicAdResponse.setDescription(description);
        publicAdResponse.setPictureUrls(pictureUrls);
        publicAdResponse.setHouseSize(houseSize);
        publicAdResponse.setGardenSize(gardenSize);
        return publicAdResponse;
    }

    public static PublicAdResponse create() {
        return create(
                randomUuid(),
                randomElement(AdTypology.FLAT, AdTypology.CHALET, AdTypology.GARAGE).toString(),
                randomSentence(),
                randomList(randomNumber(), BaseCreator::randomUuid),
                randomNumber(),
                randomNumber());
    }

}
