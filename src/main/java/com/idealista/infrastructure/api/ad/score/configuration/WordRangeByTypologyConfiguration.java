package com.idealista.infrastructure.api.ad.score.configuration;

import com.idealista.infrastructure.api.ad.score.service.scorers.impl.WordRange;
import com.idealista.infrastructure.api.ad.search.domain.AdTypology;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ConfigurationProperties(prefix="ads.description")
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordRangeByTypologyConfiguration {

    @NotEmpty
    private Map<AdTypology, List<WordRange>> wordRanges;

    public List<WordRange> getWordRangesByAdTypology(AdTypology adTypology){
        List<WordRange> wordRanges = Optional.ofNullable(this.wordRanges.get(adTypology))
            .orElse(Collections.emptyList());
        return wordRanges;
    }
}
