package com.idealista.application.service.qualityAd;

import com.idealista.domain.ad.AdTypology;
import com.idealista.util.BaseCreator;

import java.util.Date;
import java.util.List;

public final class QualityAdResponseCreator extends BaseCreator {

    public static QualityAdResponse create(String id, String typology, String description, List<String> pictureUrls, Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        final QualityAdResponse qualityAdResponse = new QualityAdResponse();
        qualityAdResponse.setId(id);
        qualityAdResponse.setTypology(typology);
        qualityAdResponse.setDescription(description);
        qualityAdResponse.setPictureUrls(pictureUrls);
        qualityAdResponse.setHouseSize(houseSize);
        qualityAdResponse.setGardenSize(gardenSize);
        qualityAdResponse.setScore(score);
        qualityAdResponse.setIrrelevantSince(irrelevantSince);
        return qualityAdResponse;
    }

    public static QualityAdResponse create() {
        return create(
                randomUuid(),
                randomElement(AdTypology.FLAT, AdTypology.CHALET, AdTypology.GARAGE).toString(),
                randomSentence(),
                randomList(randomNumber(), BaseCreator::randomUuid),
                randomNumber(),
                randomNumber(),
                randomNumber(),
                randomDate());
    }

}
