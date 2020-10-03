package com.idealista.infrastructure.properties;

import com.idealista.domain.ExtractScoreValues;
import org.springframework.stereotype.Component;

@Component
public class PropertiesExtractScoreValues implements ExtractScoreValues {

    @Override
    public int getCompleteAdScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSpecialWordScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getHDPictureScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getSDPictureScore() {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getNotPictureScore() {
        throw new UnsupportedOperationException();
    }

}
