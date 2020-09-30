package com.idealista.infrastructure.api.ad.search.mapper;

import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.dto.ScoreResponse;
import com.idealista.infrastructure.api.ad.search.dto.ScoresResponse;

import java.util.List;
import java.util.stream.Collectors;

public class AdScoreMapper {

    public static ScoreResponse adToScoreResponse(Ad ad){
        return ScoreResponse.builder()
            .adId(ad.getId())
            .score(ad.getScore())
            .build();
    }

    public static ScoresResponse adsToScoresResponse(List<Ad> ads){
        return ScoresResponse.builder()
            .scoreResponse(ads.stream().map(AdScoreMapper::adToScoreResponse).collect(Collectors.toList()))
            .build();
    }

}
