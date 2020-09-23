package com.idealista.domain.ad;

import com.idealista.application.exception.UpdateScoreNullException;
import com.idealista.domain.ad.rules.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toSet;

public class Ad {

    private String id;
    private AdTypology typology;
    private String description;
    private List<String> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    private static final Set<AdCompleteRule> COMPLETE_RULES =
            Stream.of(FlatRuleImpl.INSTANCE, ChaletRuleImpl.INSTANCE, GarageRuleImpl.INSTANCE)
                    .collect(collectingAndThen(toSet(), Collections::unmodifiableSet));

    public Ad() {}

    public Ad(String id, AdTypology typology, String description, List<String> pictures, Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictures = pictures;
        this.houseSize = houseSize;
        this.gardenSize = gardenSize;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public AdTypology getTypology() {
        return typology;
    }
    public void setTypology(AdTypology typology) {
        this.typology = typology;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPictures() {
        return pictures;
    }
    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public Integer getHouseSize() {
        return houseSize;
    }
    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }
    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

    public Integer getScore() {
        return score;
    }
    private void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }
    private void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }


    public Boolean hasDescription() {
        return StringUtils.hasText(description);
    }

    public Boolean hasPictures() {
        return !CollectionUtils.isEmpty(pictures);
    }

    public Boolean isFlat() {
        return AdTypology.FLAT.equals(typology);
    }

    public Boolean isChalet() {
        return AdTypology.CHALET.equals(typology);
    }

    public Boolean isGarage() {
        return AdTypology.GARAGE.equals(typology);
    }

    public Boolean hasHouseSize() {
        return nonNull(houseSize) && houseSize > 0;
    }

    public Boolean hasGardenSize() {
        return nonNull(gardenSize) && gardenSize > 0;
    }

    public Boolean isIrrelevant() {
        return nonNull(irrelevantSince);
    }

    public Boolean isComplete() {
        if (isNull(typology))
            return false;

        return COMPLETE_RULES.stream()
                .filter(completeRule -> completeRule.canApply(this))
                .findFirst()
                .orElse(NoOpRuleImpl.INSTANCE)
                .isComplete(this);
    }

    public void updateScore(Integer newScore, Integer minRelevantScore) {
        if (isNull(newScore)) {
            throw new UpdateScoreNullException();
        }

        final Integer previousScore = getScore();

        if (newScore >= minRelevantScore) {
            setIrrelevantSince(null);
        }
        else if (isNull(previousScore) || previousScore >= minRelevantScore) {
            setIrrelevantSince(Date.from(Instant.now()));
        }

        setScore(newScore);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ad ad = (Ad) o;
        return id.equals(ad.id) &&
                typology.equals(ad.typology) &&
                description.equals(ad.description) &&
                pictures.equals(ad.pictures) &&
                houseSize.equals(ad.houseSize) &&
                Objects.equals(gardenSize, ad.gardenSize) &&
                Objects.equals(score, ad.score) &&
                Objects.equals(irrelevantSince, ad.irrelevantSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typology, description, pictures, houseSize, gardenSize, score, irrelevantSince);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Ad.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("typology='" + typology + "'")
                .add("description='" + description + "'")
                .add("pictures=" + pictures)
                .add("houseSize=" + houseSize)
                .add("gardenSize=" + gardenSize)
                .add("score=" + score)
                .add("irrelevantSince=" + irrelevantSince)
                .toString();
    }
}
