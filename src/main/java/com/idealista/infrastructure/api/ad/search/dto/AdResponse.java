package com.idealista.infrastructure.api.ad.search.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import com.idealista.infrastructure.api.ad.search.domain.AdTypology;
import com.idealista.infrastructure.api.ad.search.domain.AdPicture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdResponse {

    private Integer id;
    private AdTypology typology;
    private String description;
    private List<AdPicture> adPictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private AdQuality adQuality;
    private Date irrelevantSince;

}
