package com.idealista.infrastructure.api.ad.score.configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
@ConfigurationProperties(prefix="ads.complete")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AdCompleteScoreConfiguration {

    @NotNull
    private Integer score;

}
