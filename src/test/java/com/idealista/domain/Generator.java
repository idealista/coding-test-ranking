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

	public static List<AdVO> generateRelevantsAdVOList(int number) {
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
	
}
