package com.idealista.infrastructure.api.ad.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.idealista.infrastructure.api.ad.search.domain.AdTypology.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ad {

    private Integer id;
    private AdTypology typology;
    private String description;
    private List<AdPicture> adPictures;
    private Integer houseSize;
    private Optional<Integer> gardenSize;
    private Integer score;
    private Optional<Date> irrelevantSince;
    private AdQuality adQuality;

    public boolean isAddIrrelevant(){
        return this.irrelevantSince.isPresent();
    }

    public boolean isAddRelevant() {
        return !this.isAddIrrelevant();
    }

    public boolean isFlat(){
        return this.typology == FLAT;
    }

    public boolean isNotFlat(){
        return !this.isFlat();
    }

    public boolean isChalet(){
        return this.typology == CHALET;
    }

    public boolean isNotChalet(){
        return !this.isChalet();
    }

    public boolean isGarage(){
        return this.typology == GARAGE;
    }

    public boolean isNotGarage(){
        return !this.isGarage();
    }

    public boolean isOfQuality(AdQuality adQuality){
        return this.adQuality == adQuality;
    }

}
