package com.idealista.domain;

import java.util.List;

public interface ExtractScoreValues {

    int getCompleteAdScore();

    int getSpecialWordScore();

    int getHDPictureScore();

    int getSDPictureScore();

    int getNotPictureScore();

    int getHasDescriptionScore();

    int getShortDescriptionScore();

    int getLongDescriptionForFlatScore();

    int getLongDescriptionForChaletScore();

    int getInitialLengthForMediumDescription();

    int getFinalLengthForMediumDescription();

    int getMaximumLengthForFlatDescription();

    int getMaximumLengthForChaletDescription();

    List<String> getSpecialWords();
}
