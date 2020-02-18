package com.idealista.infrastructure.api;

import com.idealista.application.exception.ValidationException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AdsController {

    ResponseEntity<List<QualityAd>> qualityListing(boolean seeIrrelevant);
    ResponseEntity<List<PublicAd>> publicListing();
    ResponseEntity<Void> assignScore(String id) throws ValidationException;

}
