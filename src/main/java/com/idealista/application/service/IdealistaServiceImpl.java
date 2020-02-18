package com.idealista.application.service;

import com.idealista.application.bean.Level;
import com.idealista.application.exception.IdealistaException;
import com.idealista.application.exception.dto.ErrorDTO;
import com.idealista.application.service.mapper.IdealistaMapper;
import com.idealista.infrastructure.api.PublicAd;
import com.idealista.infrastructure.api.QualityAd;
import com.idealista.infrastructure.persistence.AdVO;
import com.idealista.infrastructure.persistence.InMemoryPersistence;
import com.idealista.infrastructure.persistence.PictureVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IdealistaServiceImpl implements IdealistaService {

    private final InMemoryPersistence inMemoryPersistence;

    public IdealistaServiceImpl(InMemoryPersistence inMemoryPersistence) {
        this.inMemoryPersistence = inMemoryPersistence;
    }

    @Override
    public List<QualityAd> qualityListing(boolean seeIrrelevant) {
        inMemoryPersistence.getAds().forEach(adVO -> assignScore(adVO.getId()));

        return seeIrrelevant
                ? inMemoryPersistence.getAds().stream().filter(adVO -> adVO.getScore() < 40).map(IdealistaMapper::mapAdVOToQualityAd)
                        .sorted(Comparator.comparing(QualityAd::getIrrelevantSince)).collect(Collectors.toList())
                : inMemoryPersistence.getAds().stream().filter(adVO -> adVO.getScore() >= 40).map(IdealistaMapper::mapAdVOToQualityAd).collect(Collectors.toList());
    }

    @Override
    public List<PublicAd> publicListing() {
        return qualityListing(false).stream().
                sorted(Comparator.comparing(QualityAd::getScore))
                .map(IdealistaMapper::mapQualityAdToPublicAd)
                .collect(Collectors.toList());
    }

    @Override
    public void assignScore(Integer id) throws IdealistaException {
        AdVO adVO = inMemoryPersistence.getAds().stream().filter(ad -> ad.getId().equals(id)).findFirst().orElse(null);
        // check if adVO is not null
        if(adVO == null) throw new IdealistaException(HttpStatus.NOT_FOUND, "This 'id' is not found",
                ErrorDTO.builder().field("id").description("'id' is not valid").level(Level.ERROR).build());

        // meybe the Ad not have any photo attached
        PictureVO pictureVO = inMemoryPersistence.getPictures().stream().filter(picture -> picture.getId().equals(id)).findFirst().orElse(null);

        int score = 0;
        // the photo in Ad is in HD (20 points) / SD (10 points)
        score += Boolean.FALSE.equals(pictureVO == null) ? (pictureVO.getQuality().equals("HD") ? 20 : 10) : 0;
        // the Ad contains a non empty description (5 points)
        score += StringUtils.isNotBlank(adVO.getDescription()) ? 5 : 0;
        // the Ad is a Flat and have a 20-49 chars in description (10 points) or 50+ chars (30 points)
        score += adVO.getTypology().equals("FLAT") ?
                (adVO.getDescription().length() >= 20 && adVO.getDescription().length() <= 49 ? 10
                        : (adVO.getDescription().length() >= 50 ? 30 : 0)) : 0;
        // the Ad is a Chaler and have a 50+ chars in description (20 points)
        score += adVO.getTypology().equals("CHALET") && adVO.getDescription().length() > 50 ? 20 : 0;
        // the Ad contains one of this words (5 points)
        score += StringUtils.containsAny(adVO.getDescription(), "Luminoso", "Nuevo", "Céntrico", "Reformado", "Ático") ? 5 : 0;
        // the Ad is complete (40 points) in case of FLAT
        score += adVO.getTypology().equals("FLAT")
                && StringUtils.isNotBlank(adVO.getDescription())
                && Boolean.FALSE.equals(pictureVO == null)
                && Boolean.FALSE.equals(adVO.getHouseSize() == null)
                && adVO.getHouseSize() > 0 ? 40 : 0;
        // the Ad is complete (40 points) in case of CHALET
        score += adVO.getTypology().equals("CHALET")
                && StringUtils.isNotBlank(adVO.getDescription())
                && Boolean.FALSE.equals(pictureVO == null)
                && Boolean.FALSE.equals(adVO.getHouseSize() == null)
                && adVO.getHouseSize() > 0
                && Boolean.FALSE.equals(adVO.getGardenSize() == null)
                && adVO.getGardenSize() > 0 ? 40 : 0;
        // the Ad is complete (40 points) in case of GARAGE
        score += adVO.getTypology().equals("GARAGE")
                && Boolean.FALSE.equals(pictureVO == null)
                && Boolean.FALSE.equals(adVO.getHouseSize() == null)
                && adVO.getHouseSize() > 0 ? 40 : 0;

        // set irrelevant date if score is less than 40
        adVO.setIrrelevantSince(new Date());

        adVO.setScore(score);

    }

}
