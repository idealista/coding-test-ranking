package com.idealista.domain.score;

import static com.idealista.domain.Generator.FIFTY_WORDS_STRING;
import static com.idealista.domain.Generator.TWENTY_WORDS_STRING;
import static org.junit.Assert.assertEquals;

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
public class DescriptionLengthByTypologyCriterionTest {
	
	@Autowired
	@Qualifier("descriptionLengthByTypologyCriterion")
	private ScoreCriterion criterion;
	
	@Test
	public void whenFlatShortLengthDescription_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("FLAT");
		ad.setDescription("");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenChaletShortLengthDescription_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("CHALET");
		ad.setDescription("");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenGarage_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("GARAGE");
		ad.setDescription(FIFTY_WORDS_STRING);
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}

	@Test
	public void whenFlatMediumLenghtDescription_shouldSetPartialScoreTo10() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("FLAT");
		ad.setDescription(TWENTY_WORDS_STRING);
		
		assertEquals(10, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenFlatLongLenghtDescription_shouldSetPartialScoreTo30() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("FLAT");
		ad.setDescription(FIFTY_WORDS_STRING);
		
		assertEquals(30, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenChaletMediumLenghtDescription_shouldSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("CHALET");
		ad.setDescription(TWENTY_WORDS_STRING);
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenChaletLongLenghtDescription_shouldSetPartialScoreTo20() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setTypology("CHALET");
		ad.setDescription(FIFTY_WORDS_STRING);
		
		assertEquals(20, (int) criterion.getPartialScore(ad));
	}

}
