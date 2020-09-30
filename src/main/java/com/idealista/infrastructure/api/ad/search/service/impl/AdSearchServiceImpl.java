package com.idealista.infrastructure.api.ad.search.service.impl;

import com.idealista.infrastructure.api.ad.search.adapter.AdSearchRepositoryAdapter;
import com.idealista.infrastructure.api.ad.search.domain.Ad;
import com.idealista.infrastructure.api.ad.search.domain.AdQuality;
import com.idealista.infrastructure.api.ad.search.service.AdSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.NOT_SCORED;
import static com.idealista.infrastructure.api.ad.search.domain.AdQuality.RELEVANT;
import static java.util.Comparator.comparing;

@Service
@RequiredArgsConstructor
public class AdSearchServiceImpl implements AdSearchService {

    private final AdSearchRepositoryAdapter adSearchRepositoryAdapter;

    @Override
    public List<Ad> getAllAds(Optional<AdQuality> adQuality) {
        if (adQuality.isPresent()){
            return getAdsByQuality(adQuality.get());
        }
        return getAllAds();
    }

    @Override
    public Ad getAdById(Integer id) {
        return adSearchRepositoryAdapter.getAdById(id);
    }

    private List<Ad> getAdsByQuality(AdQuality adQuality){
        List<Ad> filteredAds = this.getAllAds().stream()
            .filter(ad -> ad.isOfQuality(adQuality))
            .collect(Collectors.toList());
        if (adQuality == RELEVANT){
            return filteredAds.stream()
                .sorted(comparing(Ad::getScore).reversed())
                .collect(Collectors.toList());
        }
        return filteredAds;
    }

    private List<Ad> getAllAds(){
        return adSearchRepositoryAdapter.getAllAds();
    }
}
