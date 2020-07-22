package com.idealista.infrastructure.api;

import java.util.List;

import com.idealista.application.service.AdService;
import com.idealista.infrastructure.mapper.AdMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
  @ApiOperation(value = "calculate score for ads")
  @ApiResponses(value = {
      @ApiResponse(code = 204, message = "No COntent")})
  public ResponseEntity<Void> calculateScore() {
    adService.calculateAdsWithScore();
    return ResponseEntity.noContent().build();
  }

  //  otro para listar los anuncios para un usuario de idealista
  @GetMapping(value = "/ads/public")
  @ApiOperation(value = "return public ads")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Not found")})
  public ResponseEntity<List<PublicAd>> publicListing() {

    List<PublicAd> result = adMapper.fromAdVoListToPublicAdList(adService.getPublicAds());
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);
  }

  //  otro para listar los anuncios para el responsable de del departamento de gestión de calidad.
  @GetMapping(value = "/ads/quality")
  @ApiOperation(value = "return quality ads")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Not found")})
  public ResponseEntity<List<QualityAd>> qualityListing() {
    List<QualityAd> result = adMapper.fromAdVoListToQualityAdList(adService.getQualityAds());
    if (result.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(result);

  }


}
