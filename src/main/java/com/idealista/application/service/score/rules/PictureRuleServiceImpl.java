package com.idealista.application.service.score.rules;

import com.idealista.application.service.picture.PictureService;
import com.idealista.domain.ad.Ad;
import com.idealista.domain.picture.Picture;
import com.idealista.domain.picture.PictureQuality;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureRuleServiceImpl implements ScoreRuleService {

    private final Integer noPicturePoints;
    private final Integer sdPicturePoints;
    private final Integer hdPicturePoints;

    private final PictureService pictureService;

    public PictureRuleServiceImpl(
            PictureService pictureService,
            @Value("${score.rules.picture.empty-points}") Integer noPicturePoints,
            @Value("${score.rules.picture.sd-points}") Integer sdPicturePoints,
            @Value("${score.rules.picture.hd-points}") Integer hdPicturePoints) {
        this.pictureService = pictureService;
        this.noPicturePoints = noPicturePoints;
        this.sdPicturePoints = sdPicturePoints;
        this.hdPicturePoints = hdPicturePoints;
    }

    @Override
    public Integer calculateScore(Ad ad) {
        if (!ad.hasPictures()) {
            return noPicturePoints;
        }

        final List<Picture> pictures = pictureService.getPictures(ad.getPictures());

        return pictures.stream()
                .mapToInt(picture -> PictureQuality.HD.equals(picture.getQuality()) ? hdPicturePoints : sdPicturePoints)
                .sum();
    }

}
