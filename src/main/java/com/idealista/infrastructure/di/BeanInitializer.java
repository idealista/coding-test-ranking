package com.idealista.infrastructure.di;

import com.idealista.application.CalculateAdsScore;
import com.idealista.domain.AdsCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class BeanInitializer {

    @Bean
    public CalculateAdsScore calculateAdsScore(final AdsCollection adsCollection) {
        return new CalculateAdsScore(adsCollection, Clock.systemDefaultZone());
    }
}
