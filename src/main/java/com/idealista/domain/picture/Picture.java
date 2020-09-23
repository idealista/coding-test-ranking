package com.idealista.domain.picture;

import java.util.Objects;
import java.util.StringJoiner;

public class Picture {

    private String id;
    private String url;
    private PictureQuality quality;

    public Picture() {}

    public Picture(String id, String url, PictureQuality quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public PictureQuality getQuality() {
        return quality;
    }
    public void setQuality(PictureQuality quality) {
        this.quality = quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return id.equals(picture.id) &&
                url.equals(picture.url) &&
                quality.equals(picture.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, quality);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Picture.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("url='" + url + "'")
                .add("quality='" + quality + "'")
                .toString();
    }
}
