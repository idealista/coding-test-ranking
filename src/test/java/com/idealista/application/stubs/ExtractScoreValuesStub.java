package com.idealista.application.stubs;

import com.idealista.domain.ExtractScoreValues;

import java.util.List;

import static java.util.Arrays.asList;

public class ExtractScoreValuesStub implements ExtractScoreValues {
    @Override
    public int getCompleteAdScore() {
        return 40;
    }

    @Override
    public int getSpecialWordScore() {
        return 5;
    }

    @Override
    public int getHDPictureScore() {
        return 20;
    }

    @Override
    public int getSDPictureScore() {
        return 10;
    }

    @Override
    public int getNotPictureScore() {
        return -10;
    }

    @Override
    public int getHasDescriptionScore() {
        return 5;
    }

    @Override
    public int getShortDescriptionScore() {
        return 10;
    }

    @Override
    public int getLongDescriptionForFlatScore() {
        return 30;
    }

    @Override
    public int getLongDescriptionForChaletScore() {
        return 20;
    }

    @Override
    public int getInitialLengthForMediumDescription() {
        return 20;
    }

    @Override
    public int getFinalLengthForMediumDescription() {
        return 49;
    }

    @Override
    public int getMaximumLengthForFlatDescription() {
        return 50;
    }

    @Override
    public int getMaximumLengthForChaletDescription() {
        return 50;
    }

    @Override
    public List<String> getSpecialWords() {
        return asList("Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático");
    }
}
