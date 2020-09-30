package com.idealista.infrastructure.api.ad.score.configuration;

import com.idealista.infrastructure.utils.StringFormatterUtils;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix="ads.description.keywords")
@Component
@Data
public class KeywordScoreConfiguration {

    @NotEmpty
    private List<String> keywords;
    @NotNull
    private Integer score;

    @PostConstruct
    public void normalizeKeywords(){
        List<String> normalizedKeywords = keywords.stream()
            .map(StringFormatterUtils::normalize)
            .collect(Collectors.toList());
        this.keywords = normalizedKeywords;
    }
}
