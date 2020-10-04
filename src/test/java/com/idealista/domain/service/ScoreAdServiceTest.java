package com.idealista.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.domain.Generator;
import com.idealista.domain.repository.AdRepository;
import com.idealista.domain.score.*;
import com.idealista.infrastructure.persistence.AdVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreAdServiceTest {
	
	@Autowired
	private ScoreAdService scoreAdService;
	
	@MockBean
	private AdRepository repository;
	
	@MockBean
	private CompletedCriterion completedCriterion;
	
	@MockBean
	private DescriptionCriterion descriptionCriterion;
	
	@MockBean
	private DescriptionKeyWordsCriterion descriptionKeyWordsCriterion;
	
	@MockBean
	private DescriptionLengthByTypologyCriterion descriptionLengthByTypologyCriterion;
	
	@MockBean
	private PictureQualityCriterion pictureQualityCriterion;
	
	@MockBean
	private PicturesCriterion picturesCriterion;
	
	private Set<ScoreCriterion> criteria;
	
	@Before
	public void setUp() {
			criteria = Set.of(completedCriterion, 
					descriptionCriterion, 
					descriptionKeyWordsCriterion,
					descriptionLengthByTypologyCriterion,
					pictureQualityCriterion,
					picturesCriterion);
	}
	
	@Test
	public void whenCalculateScore_shouldUpdateAllAdsScores() {
		List<AdVO> ads = Generator.generateAdVOList(3);

		when(repository.getAds()).thenReturn(ads);
		scoreAdService.calculateScore();
		
		verify(repository, times(1)).getAds();
		for(ScoreCriterion criterion: criteria) {
			verify(criterion, times(ads.size())).getPartialScore(any());
		}
		verify(repository, times(ads.size())).saveAd(any());
	}
	
	

}
