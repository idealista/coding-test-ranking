package com.idealista.infrastructure.api.ad.score.service.scorers.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.Range;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordRange {

    private Integer min;
    private Integer max;
    private Integer score;

    public boolean isWordCountWithinRange(Integer wordCount){
        return Range.between(min,max).contains(wordCount);
    }
}
