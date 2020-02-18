package com.idealista.application.service;

import com.idealista.application.exception.ValidationException;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IdealistaService {
    List<QualityAd> qualityListing(boolean seeIrrelevant);
    List<PublicAd> publicListing();
    void assignScore(Integer id) throws ValidationException;
}
