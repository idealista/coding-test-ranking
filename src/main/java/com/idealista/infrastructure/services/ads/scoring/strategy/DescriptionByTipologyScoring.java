package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.services.ads.common.CountWords;
import com.idealista.infrastructure.services.ads.common.ScoringRange;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DescriptionByTipologyScoring extends AbstractScoring {

    private enum DescriptionByTipologyScore {

        FLAT("FLAT") {
            @Override
            Integer execute(Integer numWords) {
                return Stream.of(
                        new ScoringRange<>(20, 49, 10),
                        new ScoringRange<>(50, Integer.MAX_VALUE, 30)
                        ).filter(r -> r.contains(numWords))
                        .map(ScoringRange::getScoring).findFirst().orElse(DEFAULT_SCORE);
            }
        },
        CHALET("CHALET") {
            @Override
            Integer execute(Integer numWords) {
                Integer score = DEFAULT_SCORE;
                if(numWords > 50) {
                    score = 20;
                }
                return score;
            }
        };

        private String typology;

        public String getTypology() {
            return typology;
        }

        DescriptionByTipologyScore(String typology) {
            this.typology = typology;
        }

        private static final Map<String, DescriptionByTipologyScore> nameIndex = Arrays.stream(DescriptionByTipologyScore.values())
                .collect(Collectors.toMap(DescriptionByTipologyScore::getTypology, Function.identity()));

        public static DescriptionByTipologyScore lookupByName(String name) {
            return nameIndex.get(name);
        }

        Integer execute(String description){
            CountWords countWords = new CountWords(description);
            return execute(countWords.count());
        }

        abstract Integer execute(Integer numWords);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public Integer calculateScoring(AdVO adVO) {
         return Optional.ofNullable(adVO.getTypology())
                    .map(DescriptionByTipologyScore::lookupByName)
                    .filter(Objects::nonNull)
                    .map(e -> e.execute(adVO.getDescription()))
                    .orElse(DEFAULT_SCORE);
    }

}
