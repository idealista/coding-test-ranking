package com.idealista.application.controller;

import com.idealista.application.exception.ValidationException;
import com.idealista.application.service.IdealistaService;
import com.idealista.application.utils.Validator;
import com.idealista.infrastructure.api.AdsController;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/idealista")
@Slf4j
public class AdsControllerImpl implements AdsController {

    private final IdealistaService idealistaService;

    public AdsControllerImpl(IdealistaService idealistaService) {
        this.idealistaService = idealistaService;
    }

    @GetMapping(value = "/quality-ads", params = {"irrelevant"})
    public ResponseEntity<List<QualityAd>> qualityListing(@RequestParam(name = "irrelevant", defaultValue = "false") boolean seeIrrelevant) {
        log.info("Calling method: -> GET /idealista/irrelevant-ads");
        // Validator.validateQualityListing();
        // calling service
        return new ResponseEntity<>(idealistaService.qualityListing(seeIrrelevant), HttpStatus.OK);
    }

    @GetMapping("/public-ads")
    public ResponseEntity<List<PublicAd>> publicListing() {
        log.info("Calling method: -> GET /idealista/public-ads");
        // Validator.validatePublicListing();
        // calling service
        return new ResponseEntity<>(idealistaService.publicListing(), HttpStatus.OK);
    }

    @PutMapping("/ad/{id}")
    public ResponseEntity<Void> assignScore(@PathVariable String id) throws ValidationException {
        log.info("Calling method: -> GET /idealista/ad/{}", id);
        Validator.validateCalculateScore(id);
        // calling service
        idealistaService.assignScore(Integer.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
