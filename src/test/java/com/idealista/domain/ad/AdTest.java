package com.idealista.domain.ad;

import com.idealista.application.exception.UpdateScoreNullException;
import com.idealista.util.BaseCreator;
import org.junit.Test;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AdTest {

    @Test
    public void shouldHaveDescription() {
        final Ad ad = new Ad();
        assertThat(ad.hasDescription()).isFalse();

        ad.setDescription("");
        assertThat(ad.hasDescription()).isFalse();

        ad.setDescription(BaseCreator.randomSentence());
        assertThat(ad.hasDescription()).isTrue();
    }

    @Test
    public void shouldHavePictures() {
        final Ad ad = new Ad();
        assertThat(ad.hasPictures()).isFalse();

        ad.setPictures(Collections.emptyList());
        assertThat(ad.hasPictures()).isFalse();

        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        assertThat(ad.hasPictures()).isTrue();
    }

    @Test
    public void shouldBeFlat() {
        final Ad ad = new Ad();
        assertThat(ad.isFlat()).isFalse();

        ad.setTypology(AdTypology.FLAT);
        assertThat(ad.isFlat()).isTrue();
    }

    @Test
    public void shouldBeChalet() {
        final Ad ad = new Ad();
        assertThat(ad.isChalet()).isFalse();

        ad.setTypology(AdTypology.CHALET);
        assertThat(ad.isChalet()).isTrue();
    }

    @Test
    public void shouldBeGarage() {
        final Ad ad = new Ad();
        assertThat(ad.isGarage()).isFalse();

        ad.setTypology(AdTypology.GARAGE);
        assertThat(ad.isGarage()).isTrue();
    }

    @Test
    public void shouldHaveHouseSize() {
        final Ad ad = new Ad();
        assertThat(ad.hasHouseSize()).isFalse();

        ad.setHouseSize(0);
        assertThat(ad.hasHouseSize()).isFalse();

        ad.setHouseSize(-1);
        assertThat(ad.hasHouseSize()).isFalse();

        ad.setHouseSize(BaseCreator.randomNumber());
        assertThat(ad.hasHouseSize()).isTrue();
    }

    @Test
    public void shouldHaveGardenSize() {
        final Ad ad = new Ad();
        assertThat(ad.hasGardenSize()).isFalse();

        ad.setGardenSize(0);
        assertThat(ad.hasGardenSize()).isFalse();

        ad.setGardenSize(-1);
        assertThat(ad.hasGardenSize()).isFalse();

        ad.setGardenSize(BaseCreator.randomNumber());
        assertThat(ad.hasGardenSize()).isTrue();
    }

    @Test
    public void shouldBeIrrelevant() {
        final int minRelevantScore = 50;

        final Ad ad = new Ad();
        assertThat(ad.isIrrelevant()).isFalse();

        ad.updateScore(25, minRelevantScore);
        assertThat(ad.isIrrelevant()).isTrue();

        ad.updateScore(49, minRelevantScore);
        assertThat(ad.isIrrelevant()).isTrue();

        ad.updateScore(50, minRelevantScore);
        assertThat(ad.isIrrelevant()).isFalse();

        ad.updateScore(75, minRelevantScore);
        assertThat(ad.isIrrelevant()).isFalse();

        ad.updateScore(100, minRelevantScore);
        assertThat(ad.isIrrelevant()).isFalse();
    }

    @Test
    public void shouldBeIncompleteWhenTypologyIsNull() {
        final Ad ad = new Ad();
        assertThat(ad.isComplete()).isFalse();
    }

    @Test
    public void shouldBeFlatComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.FLAT);
        assertThat(ad.isComplete()).isFalse();

        ad.setDescription(BaseCreator.randomSentence());
        assertThat(ad.isComplete()).isFalse();

        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        assertThat(ad.isComplete()).isFalse();

        ad.setHouseSize(BaseCreator.randomNumber());
        assertThat(ad.isComplete()).isTrue();
    }

    @Test
    public void shouldBeChaletComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.CHALET);
        assertThat(ad.isComplete()).isFalse();

        ad.setDescription(BaseCreator.randomSentence());
        assertThat(ad.isComplete()).isFalse();

        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        assertThat(ad.isComplete()).isFalse();

        ad.setHouseSize(BaseCreator.randomNumber());
        assertThat(ad.isComplete()).isFalse();

        ad.setGardenSize(BaseCreator.randomNumber());
        assertThat(ad.isComplete()).isTrue();
    }

    @Test
    public void shouldBeGarageComplete() {
        final Ad ad = new Ad();
        ad.setTypology(AdTypology.GARAGE);
        assertThat(ad.isComplete()).isFalse();

        ad.setPictures(BaseCreator.randomList(BaseCreator.randomNumber(), BaseCreator::randomUuid));
        assertThat(ad.isComplete()).isTrue();
    }

    @Test
    public void shouldUpdateScore() {
        final int minRelevantScore = 50;

        final Ad ad = new Ad();
        assertThat(ad.getScore()).isNull();
        assertThat(ad.getIrrelevantSince()).isNull();


        ad.updateScore(25, minRelevantScore);
        assertThat(ad.getScore()).isEqualTo(25);
        assertThat(ad.getIrrelevantSince()).isNotNull();
        assertThat(ad.getIrrelevantSince()).isInSameSecondWindowAs(Date.from(Instant.now()));

        final Date previousIrrelevantSince = ad.getIrrelevantSince();

        ad.updateScore(45, minRelevantScore);
        assertThat(ad.getScore()).isEqualTo(45);
        assertThat(ad.getIrrelevantSince()).isNotNull();
        assertThat(ad.getIrrelevantSince()).isEqualTo(previousIrrelevantSince);

        ad.updateScore(75, minRelevantScore);
        assertThat(ad.getScore()).isEqualTo(75);
        assertThat(ad.getIrrelevantSince()).isNull();
    }

    @Test
    public void shouldThrowUpdateScoreNullException() {
        final int minRelevantScore = 50;

        final Ad ad = new Ad();

        assertThatThrownBy(() -> ad.updateScore(null, minRelevantScore))
                .describedAs("Score cannot be updated with null value")
                .isInstanceOf(UpdateScoreNullException.class)
                .hasMessage("Score cannot be updated with null value");
    }

}
