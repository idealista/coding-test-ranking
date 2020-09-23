package com.idealista.infrastructure.persistence;

import com.idealista.domain.ad.Ad;
import com.idealista.domain.ad.AdRepository;
import com.idealista.domain.ad.AdTypology;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Repository
public class InMemoryAdPersistence implements AdRepository {

    private final List<Ad> ads;

    public InMemoryAdPersistence() {
        ads = new ArrayList<>();
        ads.add(new Ad("51370963-e38d-4718-bf28-51fdfaf58bda", AdTypology.CHALET, "Este piso es una ganga, compra, compra, COMPRA!!!!!",
                emptyList(), 300, null, null, null));
        ads.add(new Ad("4cb442c4-9aa4-4232-838c-39550d29d4ff", AdTypology.FLAT, "Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
                singletonList("5410b094-bd39-4f52-962f-8cc075514828"), 300, null, null, null));
        ads.add(new Ad("576aa3de-ebed-429e-8801-01a209bc74fe", AdTypology.CHALET, "",
                singletonList("a8bcb6c1-6183-49de-9f51-b4a6d9b55b41"), 300, null, null, null));
        ads.add(new Ad("658295d2-dac5-4de4-8671-2774a0c2f618", AdTypology.FLAT, "Ático céntrico muy luminoso y recién reformado, parece nuevo",
                singletonList("1323483f-3070-498d-8116-305b535dead7"), 300, null, null, null));
        ads.add(new Ad("ea770da3-3286-4bee-ae10-6ff02c30d639", AdTypology.FLAT, "Pisazo,",
                asList("7655de03-09dc-4624-93f8-2b19aa75e315", "5d8770bc-ace9-41c7-b391-da2b2c886884"), 300, null, null, null));
        ads.add(new Ad("bdf194ae-9922-446c-a2c2-a8c5b93ad01b", AdTypology.GARAGE, "",
                singletonList("ab214736-0613-4009-a247-c7a60debdb1f"), 300, null, null, null));
        ads.add(new Ad("6337ba7e-3a6a-4560-83ba-a23c7cc0238e", AdTypology.GARAGE, "Garaje en el centro de Albacete",
                emptyList(), 300, null, null, null));
        ads.add(new Ad("ce1befa5-92b3-423a-acd5-a97f05ebb1a8", AdTypology.CHALET, "Maravilloso chalet situado en lAs afueras de un pequeño pueblo rural. El entorno es espectacular, las vistas magníficas. ¡Cómprelo ahora!",
                asList("bd962976-31fe-4442-8a83-b0c865034730", "75adac83-c2dd-46bf-9cd6-fc1c6331a56c"), 300, null, null, null));
    }


    @Override
    public List<Ad> getAllAds() {
        return ads;
    }

    @Override
    public void saveAd(Ad ad) {
        final Optional<Ad> old = findAdById(ad.getId());
        if (old.isPresent()) {
            final int index = ads.indexOf(old.get());
            ads.set(index, ad);
        } else {
            ads.add(ad);
        }
    }

    @Override
    public Optional<Ad> findAdById(String id) {
        return ads.stream()
                .filter(ad -> ad.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Ad> findPublicAds() {
        return ads.stream()
                .filter(ad -> nonNull(ad.getScore()))
                .filter(ad -> !ad.isIrrelevant())
                .collect(Collectors.toList());
    }

    @Override
    public List<Ad> findIrrelevantAds() {
        return ads.stream()
                .filter(ad -> isNull(ad.getScore()) || ad.isIrrelevant())
                .collect(Collectors.toList());
    }


}
