package com.idealista.domain.score;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.infrastructure.persistence.AdVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DescriptionCriterionTest {
	
	@Autowired
	@Qualifier("descriptionCriterion")
	private ScoreCriterion scoreCriterion;

	@Test
	public void whenExistsDescription_shouldSetPartialScoreTo5() {
		AdVO ad = new AdVO();
		ad.setDescription("New description");
		
		assertEquals(5, (int) scoreCriterion.getPartialScore(ad));
	}
	
	@Test
	public void whenNotExistsDescription_shouldSetPartialScoreTo0() {
		AdVO ad = new AdVO();
		
		assertEquals(0, (int) scoreCriterion.getPartialScore(ad));
	}
	
}
