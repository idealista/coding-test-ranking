package com.idealista.domain.service;

import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.domain.Generator;
import com.idealista.domain.repository.AdRepository;
import com.idealista.infrastructure.persistence.AdVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicAdServiceTest {
	
	@Autowired
	private PublicAdService service;
	
	@MockBean
	private AdRepository repository;

	@Test
	public void whenNoRelevantAds_shouldReturnEmptyList() {
		when(repository.getAds()).thenReturn(Collections.emptyList());
		
		assertEquals(Collections.emptyList(), service.getAds());
	}
	
	@Test
	public void whenThereAreTwoRelevantAds_shouldReturnTwoSizeList() {
		List<AdVO> ads = Generator.generateRelevantAdsVOList(2);
		
		when(repository.getAds()).thenReturn(ads);
		
		assertEquals(ads.size(), service.getAds().size());
	}
	
	@Test
	public void whenThereAreThreeRelevantAdsAndTwoIrrelevantAds_shouldReturnThreeSizeList() {
		List<AdVO> relevantAds = Generator.generateRelevantAdsVOList(2);
		List<AdVO> irrelevantAds = Generator.generateAdVOList(2);
		List<AdVO> ads = Generator.joinLists(relevantAds, irrelevantAds);
		
		when(repository.getAds()).thenReturn(ads);
		
		assertEquals(relevantAds.size(), service.getAds().size());
	}

}
