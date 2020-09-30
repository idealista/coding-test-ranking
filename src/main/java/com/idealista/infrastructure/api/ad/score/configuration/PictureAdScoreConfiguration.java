package com.idealista.infrastructure.api.ad.score.configuration;

import com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Optional;

@ConfigurationProperties(prefix="ads")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PictureAdScoreConfiguration {

    @NotNull
    private Map<AdPictureQuality, Integer> pictureQualityScore;
    @NotNull
    private Integer noPicturePenalty;

    public Integer getScoreByPictureQuality(AdPictureQuality adPictureQuality){
        return Optional.ofNullable(pictureQualityScore.get(adPictureQuality)).orElse(0);
    }
}
