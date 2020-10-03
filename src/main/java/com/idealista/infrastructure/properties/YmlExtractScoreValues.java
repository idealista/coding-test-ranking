package com.idealista.infrastructure.properties;

import com.idealista.domain.ExtractScoreValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class YmlExtractScoreValues implements ExtractScoreValues {

    @Value("${condition.score.complete_ad_score}")
    private int completeAdScore;

    @Value("${condition.score.special_word_score}")
    private int specialWordScore;

    @Value("${condition.score.high_definition_picture_score}")
    private int highDefinitionPictureScore;

    @Value("${condition.score.standard_definition_picture_score}")
    private int standardDefinitionPictureScore;

    @Value("${condition.score.not_picture_score}")
    private int notPictureScore;

    @Value("${condition.score.has_description_score}")
    private int hasDescriptionScore;

    @Value("${condition.score.short_description_score}")
    private int shortDescriptionScore;

    @Value("${condition.score.long_description_flat_score}")
    private int longDescriptionFlatScore;

    @Value("${condition.score.long_description_chalet_score}")
    private int longDescriptionChaletScore;


    @Override
    public int getCompleteAdScore() {
        return completeAdScore;
    }

    @Override
    public int getSpecialWordScore() {
        return specialWordScore;
    }

    @Override
    public int getHDPictureScore() {
        return highDefinitionPictureScore;
    }

    @Override
    public int getSDPictureScore() {
        return standardDefinitionPictureScore;
    }

    @Override
    public int getNotPictureScore() {
        return notPictureScore;
    }

    @Override
    public int getHasDescriptionScore() {
        return hasDescriptionScore;
    }

    @Override
    public int getShortDescriptionScore() {
        return shortDescriptionScore;
    }

    @Override
    public int getLongDescriptionForFlatScore() {
        return longDescriptionFlatScore;
    }

    @Override
    public int getLongDescriptionForChaletScore() {
        return longDescriptionChaletScore;
    }

}
