package com.idealista.infrastructure.api.ad.search.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.idealista.infrastructure.api.ad.search.domain.AdPictureQuality.HD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPicture {

    private Integer id;
    private String url;
    private AdPictureQuality quality;

    public boolean isPictureHighQuality(){
        return this.quality.equals(HD);
    }
}
