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
public class CompletedCriterionTest {
	
	@Autowired
	@Qualifier("completedCriterion")
	private ScoreCriterion criterion;
	
	@Test
	public void whenFlatAdIsNotCompleted_shouldsetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("FLAT");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenChaletAdIsNotCompleted_shouldsetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("CHALET");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenGarageAdIsNotCompleted_shouldsetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("GARAGE");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenFlatAdIsCompleted_shouldsetPartialScoreTo40() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("FLAT");
		ad.setDescription("Flat description");
		ad.setPictures(List.of(1));
		ad.setHouseSize(80);
		
		assertEquals(40, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenChaletAdIsCompleted_shouldsetPartialScoreTo40() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("CHALET");
		ad.setDescription("Chalet description");
		ad.setPictures(List.of(1));
		ad.setHouseSize(80);
		ad.setGardenSize(20);
		
		
		assertEquals(40, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenGarageAdIsCompleted_shouldsetPartialScoreTo40() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("GARAGE");
		ad.setPictures(List.of(1));
		
		assertEquals(40, (int) criterion.getPartialScore(ad));
	}
}
