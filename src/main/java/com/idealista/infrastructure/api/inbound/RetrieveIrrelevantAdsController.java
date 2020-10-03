package com.idealista.infrastructure.api.inbound;

import com.idealista.application.RetrieveIrrelevantAds;
import com.idealista.domain.Ad;
import com.idealista.infrastructure.api.inbound.dto.QualityAd;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RetrieveIrrelevantAdsController {

    private final RetrieveIrrelevantAds retrieveIrrelevantAds;

    private final Function<Ad, QualityAd> mapper;

    public RetrieveIrrelevantAdsController(RetrieveIrrelevantAds retrieveIrrelevantAds, Function<Ad, QualityAd> mapper) {
        this.retrieveIrrelevantAds = retrieveIrrelevantAds;
        this.mapper = mapper;
    }

    @GetMapping("/api/1/ad/irrelevant-ads")
    public ResponseEntity<List<QualityAd>> qualityListing() {
        final List<QualityAd> irrelevantAds = retrieveIrrelevantAds.execute()
            .stream()
            .map(mapper)
            .collect(Collectors.toList());
        return ResponseEntity.ok(irrelevantAds);
    }
}
