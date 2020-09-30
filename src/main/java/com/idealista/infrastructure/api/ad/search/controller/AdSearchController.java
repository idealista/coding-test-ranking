package com.idealista.infrastructure.api.ad.search.controller;

import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import com.idealista.infrastructure.api.ad.search.dto.AdResponse;
import com.idealista.infrastructure.api.ad.search.dto.AdsResponse;
import com.idealista.infrastructure.api.ad.search.service.AdSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import java.util.Optional;

import static com.idealista.infrastructure.api.ad.search.mapper.AdMapper.adToAdResponse;
import static com.idealista.infrastructure.api.ad.search.mapper.AdMapper.adsToAdsResponse;

@RestController
@RequiredArgsConstructor
public class AdSearchController {

    private final AdSearchService adSearchService;

    @GetMapping("/ads")
    public AdsResponse getAllAds(@RequestParam("quality") Optional<AdQuality> adQuality){
        return adsToAdsResponse(adSearchService.getAllAds(adQuality));
    }

    @GetMapping("/ads/{adId}")
    public AdResponse getAdById(@PathVariable @NotNull Integer adId){
        return adToAdResponse(adSearchService.getAdById(adId));
    }
}
