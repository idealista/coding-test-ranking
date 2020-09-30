package com.idealista.infrastructure.api.ad.score.configuration;

import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.IRRELEVANT;
import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.RELEVANT;

@Component
@ConfigurationProperties(prefix="ads.score-bounds")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdScoreConfiguration {

    @NotNull
    private Integer max;
    @NotNull
    private Integer min;
    @NotNull
    private Integer relevantMinScore;

    public Integer clampScore(Integer score){
        if (score.compareTo(max) > 0){
            return max;
        }
        if (score.compareTo(min) < 0){
            return min;
        }
        return score;
    }

    public AdQuality getAdQuality(Integer score){
        return score.compareTo(relevantMinScore) >= 0 ? RELEVANT : IRRELEVANT;
    }
}
