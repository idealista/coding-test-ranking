package com.idealista.infrastructure.api.inbound;

import com.idealista.application.RetrievePublicAds;
import com.idealista.domain.Ad;
import com.idealista.infrastructure.api.inbound.dto.PublicAd;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public final class RetrievePublicAdsController {

  private final RetrievePublicAds retrievePublicAds;

  private final Function<Ad, PublicAd> mapper;

  public RetrievePublicAdsController(RetrievePublicAds retrievePublicAds, Function<Ad, PublicAd> mapper) {
    this.retrievePublicAds = retrievePublicAds;
    this.mapper = mapper;
  }

  @GetMapping("/api/1/ad/public-ads")
  public ResponseEntity<List<PublicAd>> calculateScore() {
    final List<PublicAd> publicAds = retrievePublicAds.execute().stream()
        .map(mapper)
        .collect(Collectors.toList());
    return ResponseEntity.ok().body(publicAds);
  }
}
