package com.idealista.infrastructure.api.ad.score.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="ads.description.descriptive")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdDescriptionScoreConfiguration {

    private Integer score;

}
