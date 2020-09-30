package com.idealista.infrastructure.api.ad.score.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigurationProperties(prefix="ads.description.keywords")
@Component
@Data
public class KeywordScoreConfiguration {

    private List<String> keywords;
    private Integer score;

}
