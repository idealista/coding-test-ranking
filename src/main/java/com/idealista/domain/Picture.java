package com.idealista.domain;

import java.util.Objects;

public final class Picture {

    private final Integer id;
    private final String url;
    private final String quality;

    public Picture(Integer id, String url, String quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public Integer getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getQuality() {
        return quality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Picture picture = (Picture) o;
        return Objects.equals(id, picture.id) &&
                Objects.equals(url, picture.url) &&
                Objects.equals(quality, picture.quality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, quality);
    }

    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", quality='" + quality + '\'' +
                '}';
    }
}
