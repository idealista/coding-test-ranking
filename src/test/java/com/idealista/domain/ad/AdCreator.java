package com.idealista.domain.ad;

import com.idealista.util.BaseCreator;

import java.util.Date;
import java.util.List;

public final class AdCreator extends BaseCreator {

    public static Ad create(String id, AdTypology typology, String description, List<String> pictures, Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        return new Ad(id, typology, description, pictures, houseSize, gardenSize, score, irrelevantSince);
    }

    public static Ad create() {
        return create(false);
    }

    public static Ad create(Boolean withScore) {
        final Integer score = withScore ? randomNumber() : null;
        final Date irrelevantSince = withScore ? randomDate() : null;

        return create(
                randomUuid(),
                randomElement(AdTypology.FLAT, AdTypology.CHALET, AdTypology.GARAGE),
                randomSentence(),
                randomList(randomNumber(), BaseCreator::randomUuid),
                randomNumber(),
                randomNumber(),
                score,
                irrelevantSince);
    }

}
