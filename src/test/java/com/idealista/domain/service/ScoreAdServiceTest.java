package com.idealista.domain.service;

import static com.idealista.domain.score.CriterionType.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;

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
	private ScoreCriterionFactory scoreCriterionFactory;
	
	@Test
	public void whenCalculateScore_shouldUpdateAllAdsScores() {
		List<AdVO> ads = Generator.generateAdVOList(3);

		when(repository.getAds()).thenReturn(ads);
		when(scoreCriterionFactory.getCriterion(PICTURES_CRITERION)).thenReturn(new PicturesCriterion());
		when(scoreCriterionFactory.getCriterion(DESCRIPTION_CRITERION)).thenReturn(new DescriptionCriterion());
		when(scoreCriterionFactory.getCriterion(COMPLETED_CRITERION)).thenReturn(new CompletedCriterion());
		scoreAdService.calculateScore();
		
		verify(repository, times(1)).getAds();
		verify(scoreCriterionFactory, times(ads.size() * CriterionType.values().length)).getCriterion(any());
		verify(repository, times(ads.size())).saveAd(any());
	}
	
	

}
