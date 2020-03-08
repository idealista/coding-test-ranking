package com.idealista.infrastructure.controllers;

import java.util.Date;
import java.util.List;

public class QualityAd extends PublicAd{


    private Integer score;
    private Date irrelevantSince;


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getIrrelevantSince() {
        return irrelevantSince;
    }

    public void setIrrelevantSince(Date irrelevantSince) {
        this.irrelevantSince = irrelevantSince;
    }
}
