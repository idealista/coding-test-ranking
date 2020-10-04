package com.idealista.domain.score;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.idealista.domain.Generator;
import com.idealista.infrastructure.persistence.AdVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureCriterionTest {
	
	@Autowired
	@Qualifier("picturesCriterion")
	private ScoreCriterion criterion;

	@Test
	public void whenAdNotContainsPictures_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		
		assertEquals(-10, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenAdContainsPictures_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setPictures(List.of(1));
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	

}
