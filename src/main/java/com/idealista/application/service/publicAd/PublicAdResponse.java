package com.idealista.application.service.publicAd;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class PublicAdResponse {

    private String id;
    private String typology;
    private String description;
    private List<String> pictureUrls;
    private Integer houseSize;
    private Integer gardenSize;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PublicAdResponse that = (PublicAdResponse) o;
        return id.equals(that.id) &&
                typology.equals(that.typology) &&
                description.equals(that.description) &&
                pictureUrls.equals(that.pictureUrls) &&
                houseSize.equals(that.houseSize) &&
                Objects.equals(gardenSize, that.gardenSize);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typology, description, pictureUrls, houseSize, gardenSize);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", PublicAdResponse.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("typology='" + typology + "'")
                .add("description='" + description + "'")
                .add("pictureUrls=" + pictureUrls)
                .add("houseSize=" + houseSize)
                .add("gardenSize=" + gardenSize)
                .toString();
    }
}
