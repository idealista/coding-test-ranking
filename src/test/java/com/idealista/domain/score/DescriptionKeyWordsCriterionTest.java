package com.idealista.domain.score;

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
public class DescriptionKeyWordsCriterionTest {

	
	@Autowired
	@Qualifier("descriptionKeyWordsCriterion")
	private ScoreCriterion criterion;
	
	@Test
	public void whenNoKeyWordsInDescription_shoulSetPartialScoreTo0() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setDescription("");
		
		assertEquals(0, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenCaseSensitiveKeyWordsInDescription_shouldAdd5ToPartialScoreForEachOne() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setDescription("luminoso nuevo centrico reformado atico");
		
		assertEquals(25, (int) criterion.getPartialScore(ad));
	}
	
	@Test
	public void whenCaseInsensitiveKeyWordsInDescription_shouldAdd5ToPartialScoreForEachOne() {
		AdVO ad = Generator.generateNullAdVO();
		ad.setDescription("LUMINOSO Nuevo céntrICO reformado Ático");
		
		assertEquals(25, (int) criterion.getPartialScore(ad));
	}

}
