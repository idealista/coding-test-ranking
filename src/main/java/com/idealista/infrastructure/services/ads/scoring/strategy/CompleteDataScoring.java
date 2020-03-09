package com.idealista.infrastructure.services.ads.scoring.strategy;

import com.idealista.infrastructure.entities.AdVO;
import com.idealista.infrastructure.services.ads.common.AdVOConditions;
import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompleteDataScoring extends AbstractScoring { //implements Scoring

    private static Integer SCORE = 40;

    private enum CompleteDataByTipology {

        FLAT("FLAT") {
            @Override
            Boolean execute(AdVO adVO) {
                return Stream.of(()-> DEFAULT.execute(adVO), AdVOConditions.hasHouseSize(adVO))
                        .map(BooleanSupplier::getAsBoolean).reduce(true, Boolean::logicalAnd);
            }
        },
        CHALET("CHALET") {
            @Override
            Boolean execute(AdVO adVO) {
                return Stream.of(()-> FLAT.execute(adVO), AdVOConditions.hasGardenSize(adVO))
                    .map(BooleanSupplier::getAsBoolean).reduce(true, Boolean::logicalAnd);
            }
        },
        GARAJE("GARAJE") {
            @Override
            Boolean execute(AdVO adVO) {
                return AdVOConditions.hasPhoto(adVO).getAsBoolean();
            }
        },
        DEFAULT("DEFAULT") {
            @Override
            Boolean execute(AdVO adVO) {
                return Stream.of(AdVOConditions.hasDescription(adVO), AdVOConditions.hasPhoto(adVO))
                        .map(BooleanSupplier::getAsBoolean).reduce(true, Boolean::logicalAnd);
            }
        };

        private String typology;

        public String getTypology() {
            return typology;
        }

        CompleteDataByTipology(String typology) {
            this.typology = typology;
        }

        private static final Map<String, CompleteDataByTipology> nameIndex =
                Arrays.stream(CompleteDataByTipology.values())
                        .collect(Collectors.toMap(CompleteDataByTipology::getTypology, Function.identity()));

        public static CompleteDataByTipology lookupByName(String name) {
            return ObjectUtils.defaultIfNull(nameIndex.get(name), DEFAULT);
        }

        abstract Boolean execute(AdVO adVO);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public Integer calculateScoring(AdVO adVO) {
       return Optional.ofNullable(adVO.getTypology())
                .map(CompleteDataByTipology::lookupByName)
                .filter(Objects::nonNull)
                .map(e -> e.execute(adVO))
                .filter(Boolean::booleanValue)
                .map(e -> SCORE)
                .orElse(DEFAULT_SCORE);
    }
}
