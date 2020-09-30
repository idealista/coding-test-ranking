package com.idealista.infrastructure.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.idealista.infrastructure.persistence.PictureQuality.HD;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Picture {

    private Integer id;
    private String url;
    private PictureQuality quality;

    public boolean isPictureHighQuality(){
        return this.quality.equals(HD);
    }
}
