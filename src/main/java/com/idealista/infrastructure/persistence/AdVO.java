package com.idealista.infrastructure.persistence;

import java.util.Date;
import java.util.List;

public class AdVO {

    private static final int MAX_SCORE = 100;
	private static final int MIN_SCORE = 0;
	private static final int RELEVANT_SCORE_THRESHOLD = 40;
    
	private Integer id;
    private String typology;
    private String description;
    private List<Integer> pictures;
    private Integer houseSize;
    private Integer gardenSize;
    private Integer score;
    private Date irrelevantSince;

    public AdVO() {}

    public AdVO(Integer id, String typology, String description, List<Integer> pictures, Integer houseSize, Integer gardenSize, Integer score, Date irrelevantSince) {
        this.id = id;
        this.typology = typology;
        this.description = description;
        this.pictures = pictures;
        this.houseSize = houseSize;
        this.gardenSize = gardenSize;
        this.score = score;
        this.irrelevantSince = irrelevantSince;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypology() {
        return typology;
    }

    public void setTypology(String typology) {
        this.typology = typology;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getPictures() {
        return pictures;
    }

    public void setPictures(List<Integer> pictures) {
        this.pictures = pictures;
    }

    public Integer getHouseSize() {
        return houseSize;
    }

    public void setHouseSize(Integer houseSize) {
        this.houseSize = houseSize;
    }

    public Integer getGardenSize() {
        return gardenSize;
    }

    public void setGardenSize(Integer gardenSize) {
        this.gardenSize = gardenSize;
    }

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

	public boolean isRelevant() {
		return this.score != null && this.score >= RELEVANT_SCORE_THRESHOLD;
	}

	public void updateScore(int modifier) {
		if(score == null) {
			score = 0;
		}
		score += modifier;
		fitScoreToBoundaries();
	}

	private void fitScoreToBoundaries() {
		if(score<MIN_SCORE) {
			score = MIN_SCORE;
		} else if (score>MAX_SCORE) {
			score = MAX_SCORE;
		}
	}
	
	public int getDescriptionWordsLength() {
		return description.split("\\s+").length;
	}
	
	public boolean isFlatAd() {
		return typology.equals("FLAT");
	}
	
	public boolean isChaletAd() {
		return typology.equals("CHALET");
	}

	public boolean isGarageAd() {
		return typology.equals("GARAGE");
	}

	public boolean hasPictures() {
		return pictures !=null && pictures.size()>0;
	}

	public boolean hasDescription() {
		return description != null && !description.isBlank();
	}

	public boolean isCompleted() {
		if(isFlatAd()) {
			return hasDescription() 
					&& hasPictures()
					&& houseSize!=null && houseSize>0;
		} else if(isChaletAd()) {
			return hasDescription()
					&& hasPictures()
					&& houseSize!=null && houseSize>0
					&& gardenSize!=null && gardenSize>0;
		} else if(isGarageAd()) {
			return hasPictures();
		}
		return false;
	}
}
