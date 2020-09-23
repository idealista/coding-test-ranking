package com.idealista.application.service.qualityAd;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class QualityAdResponse {

    private String id;
    private String typology;
    private String description;
    private List<String> pictureUrls;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTypology() {
        return typology;
    }
    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getPictureUrls() {
        return pictureUrls;
    }
    public void setPictureUrls(List<String> pictureUrls) {
        this.pictureUrls = pictureUrls;
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
    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }
    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QualityAdResponse that = (QualityAdResponse) o;
        return id.equals(that.id) &&
                typology.equals(that.typology) &&
                description.equals(that.description) &&
                pictureUrls.equals(that.pictureUrls) &&
                houseSize.equals(that.houseSize) &&
                Objects.equals(gardenSize, that.gardenSize) &&
                Objects.equals(score, that.score) &&
                Objects.equals(irrelevantSince, that.irrelevantSince);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typology, description, pictureUrls, houseSize, gardenSize, score, irrelevantSince);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", QualityAdResponse.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("typology='" + typology + "'")
                .add("description='" + description + "'")
                .add("pictureUrls=" + pictureUrls)
                .add("houseSize=" + houseSize)
                .add("gardenSize=" + gardenSize)
                .add("score=" + score)
                .add("irrelevantSince=" + irrelevantSince)
                .toString();
    }
}
