package com.idealista.infrastructure.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdsController {

  //TODO añade url del endpoint
  @GetMapping(value = "/custom")
  public ResponseEntity<List<QualityAd>> qualityListing() {
    //TODO rellena el cuerpo del método
    return ResponseEntity.notFound().build();
  }

  //TODO añade url del endpoint
  @GetMapping(value = "/publicListing")
  public ResponseEntity<List<PublicAd>> publicListing() {
    //TODO rellena el cuerpo del método
    return ResponseEntity.notFound().build();
  }

  //TODO añade url del endpoint
  @GetMapping(value = "/score")
  public ResponseEntity<Void> calculateScore() {
    //TODO rellena el cuerpo del método
    return ResponseEntity.notFound().build();
  }
}
