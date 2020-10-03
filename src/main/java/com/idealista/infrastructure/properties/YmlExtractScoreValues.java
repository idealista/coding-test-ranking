package com.idealista.infrastructure.properties;

import com.idealista.domain.ExtractScoreValues;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "ads")
public class YmlExtractScoreValues implements ExtractScoreValues {

    private Conditions conditionsScores;

    private List<String> specialWords;

    public void setConditionsScores(Conditions conditionsScores) {
        this.conditionsScores = conditionsScores;
    }

    public void setSpecialWords(List<String> specialWords) {
        this.specialWords = specialWords;
    }

    @Override
    public int getCompleteAdScore() {
        return conditionsScores.getCompleteAdScore();
    }

    @Override
    public int getSpecialWordScore() {
        return conditionsScores.getSpecialWordScore();
    }

    @Override
    public int getHDPictureScore() {
        return conditionsScores.getHighDefinitionPictureScore();
    }

    @Override
    public int getSDPictureScore() {
        return conditionsScores.getStandardDefinitionPictureScore();
    }

    @Override
    public int getNotPictureScore() {
        return conditionsScores.getNotPictureScore();
    }

    @Override
    public int getHasDescriptionScore() {
        return conditionsScores.getHasDescriptionScore();
    }

    @Override
    public int getShortDescriptionScore() {
        return conditionsScores.getShortDescriptionScore();
    }

    @Override
    public int getLongDescriptionForFlatScore() {
        return conditionsScores.getLongDescriptionFlatScore();
    }

    @Override
    public int getLongDescriptionForChaletScore() {
        return conditionsScores.getLongDescriptionChaletScore();
    }

    @Override
    public int getInitialLengthForMediumDescription() {
        return conditionsScores.getInitialLengthForMediumDescriptionScore();
    }

    @Override
    public int getFinalLengthForMediumDescription() {
        return conditionsScores.getFinalLengthForMediumDescription();
    }

    @Override
    public int getMaximumLengthForFlatDescription() {
        return conditionsScores.getMaximumLengthForFlatDescriptionScore();
    }

    @Override
    public int getMaximumLengthForChaletDescription() {
        return conditionsScores.getMaximumLengthForChaletDescriptionScore();
    }

    @Override
    public List<String> getSpecialWords() {
        return specialWords;
    }

    static class Conditions{

        private int completeAdScore;

        private int specialWordScore;

        private int highDefinitionPictureScore;

        private int standardDefinitionPictureScore;

        private int notPictureScore;

        private int hasDescriptionScore;

        private int shortDescriptionScore;

        private int longDescriptionFlatScore;

        private int longDescriptionChaletScore;

        private int initialLengthForMediumDescriptionScore;

        private int finalLengthForMediumDescription;

        private int maximumLengthForFlatDescriptionScore;

        private int maximumLengthForChaletDescriptionScore;

        public int getCompleteAdScore() {
            return completeAdScore;
        }

        public void setCompleteAdScore(int completeAdScore) {
            this.completeAdScore = completeAdScore;
        }

        public int getSpecialWordScore() {
            return specialWordScore;
        }

        public void setSpecialWordScore(int specialWordScore) {
            this.specialWordScore = specialWordScore;
        }

        public int getHighDefinitionPictureScore() {
            return highDefinitionPictureScore;
        }

        public void setHighDefinitionPictureScore(int highDefinitionPictureScore) {
            this.highDefinitionPictureScore = highDefinitionPictureScore;
        }

        public int getStandardDefinitionPictureScore() {
            return standardDefinitionPictureScore;
        }

        public void setStandardDefinitionPictureScore(int standardDefinitionPictureScore) {
            this.standardDefinitionPictureScore = standardDefinitionPictureScore;
        }

        public int getNotPictureScore() {
            return notPictureScore;
        }

        public void setNotPictureScore(int notPictureScore) {
            this.notPictureScore = notPictureScore;
        }

        public int getHasDescriptionScore() {
            return hasDescriptionScore;
        }

        public void setHasDescriptionScore(int hasDescriptionScore) {
            this.hasDescriptionScore = hasDescriptionScore;
        }

        public int getShortDescriptionScore() {
            return shortDescriptionScore;
        }

        public void setShortDescriptionScore(int shortDescriptionScore) {
            this.shortDescriptionScore = shortDescriptionScore;
        }

        public int getLongDescriptionFlatScore() {
            return longDescriptionFlatScore;
        }

        public void setLongDescriptionFlatScore(int longDescriptionFlatScore) {
            this.longDescriptionFlatScore = longDescriptionFlatScore;
        }

        public int getLongDescriptionChaletScore() {
            return longDescriptionChaletScore;
        }

        public void setLongDescriptionChaletScore(int longDescriptionChaletScore) {
            this.longDescriptionChaletScore = longDescriptionChaletScore;
        }

        public int getInitialLengthForMediumDescriptionScore() {
            return initialLengthForMediumDescriptionScore;
        }

        public void setInitialLengthForMediumDescriptionScore(int initialLengthForMediumDescriptionScore) {
            this.initialLengthForMediumDescriptionScore = initialLengthForMediumDescriptionScore;
        }

        public int getFinalLengthForMediumDescription() {
            return finalLengthForMediumDescription;
        }

        public void setFinalLengthForMediumDescription(int finalLengthForMediumDescription) {
            this.finalLengthForMediumDescription = finalLengthForMediumDescription;
        }

        public int getMaximumLengthForFlatDescriptionScore() {
            return maximumLengthForFlatDescriptionScore;
        }

        public void setMaximumLengthForFlatDescriptionScore(int maximumLengthForFlatDescriptionScore) {
            this.maximumLengthForFlatDescriptionScore = maximumLengthForFlatDescriptionScore;
        }

        public int getMaximumLengthForChaletDescriptionScore() {
            return maximumLengthForChaletDescriptionScore;
        }

        public void setMaximumLengthForChaletDescriptionScore(int maximumLengthForChaletDescriptionScore) {
            this.maximumLengthForChaletDescriptionScore = maximumLengthForChaletDescriptionScore;
        }
    }

}
