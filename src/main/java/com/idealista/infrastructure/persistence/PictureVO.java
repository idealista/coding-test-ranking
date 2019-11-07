package com.idealista.infrastructure.persistence;

public class PictureVO {
    private static final String HD = "HD";
    private static final String SD = "SD";

    private Integer id;
    private String url;
    private String quality;

    public PictureVO() {}

    public PictureVO(Integer id, String url, String quality) {
        this.id = id;
        this.url = url;
        this.quality = quality;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    /*
        My own methods
     */
    public boolean isHD() { return quality.equals(HD); }

    public boolean isSD() { return quality.equals(SD); }
}
