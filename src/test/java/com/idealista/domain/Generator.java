package com.idealista.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.idealista.infrastructure.persistence.AdVO;

public class Generator {
	
	public static final String TWENTY_WORDS_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum dictum vehicula sapien. Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
	public static final String FIFTY_WORDS_STRING = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam sed mauris ornare, consectetur ipsum tempus, vehicula quam. Vestibulum efficitur libero erat, id ultrices velit efficitur ut. Curabitur rhoncus arcu sed augue hendrerit, eget ultrices tellus fermentum. Vestibulum viverra dolor ut lacinia maximus. Proin sollicitudin convallis urna sed viverra. Phasellus at.";
	
	public static AdVO generateNullAdVO() {
		return new AdVO();
	}
	
	public static List<AdVO> generateAdVOList(int number){
		List<AdVO> ads = new ArrayList<AdVO>();
		for(int i = 0; i<number; i++) {
			ads.add(new AdVO(i, "", "desc. "+i, Collections.emptyList(), 0, 0, 0, new Date()));
		}
		return ads;
	}

	public static List<AdVO> generateRelevantAdsVOList(int number) {
		List<AdVO> ads = new ArrayList<AdVO>();
		for(int i = 0; i<number; i++) {
			ads.add(new AdVO(i, "", "", Collections.emptyList(), 0, 0, 40+i, new Date()));
		}
		return ads;
	}

	@SafeVarargs
	public static <T> List<T> joinLists(List<T>... lists) {
        return Arrays.stream(lists)
        		.flatMap(Collection::stream)
        		.collect(Collectors.toList()); 
	}

	public static List<AdVO> generateIrrelevantAdsVOList(int number) {
		List<AdVO> ads = new ArrayList<AdVO>();
		for(int i = 0; i<number; i++) {
			ads.add(new AdVO(-i, "", "", Collections.emptyList(), 0, 0, 39-i, new Date()));
		}
		return ads;
	}
	
}
