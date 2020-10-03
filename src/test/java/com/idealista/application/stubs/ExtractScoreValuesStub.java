package com.idealista.application.stubs;

import com.idealista.domain.ExtractScoreValues;

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
}
