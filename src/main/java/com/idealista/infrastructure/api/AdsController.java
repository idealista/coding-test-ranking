package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.application.service.AdService;
import com.idealista.infrastructure.mapper.AdMapper;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Api(tags = {"Ads"})
public class AdsController {
  private final AdService adService;
  private final AdMapper adMapper;

  //  * Debes proporcionar 3 endpoints: Uno para calcular la puntuación de todos los anuncios,
  @GetMapping(value = "/ads/score")
  public ResponseEntity<Void> calculateScore() {
    adService.calculateAdsWithScore();
    return ResponseEntity.noContent().build();
  }

  //  otro para listar los anuncios para un usuario de idealista
  @GetMapping(value = "/ads/public")
  public ResponseEntity<List<PublicAd>> publicListing() {

    List<PublicAd> result = adMapper.fromAdVoListToPublicAdList(adService.getPublicAds());
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);
  }

  //  otro para listar los anuncios para el responsable de del departamento de gestión de calidad.
  @GetMapping(value = "/ads/quality")
  public ResponseEntity<List<QualityAd>> qualityListing() {
    List<QualityAd> result = adMapper.fromAdVoListToQualityAdList(adService.getQualityAds());
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);

  }


}
