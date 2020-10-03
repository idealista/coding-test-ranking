package com.idealista.domain.score;

import static com.idealista.domain.score.CriterionType.COMPLETED_CRITERION;
import static com.idealista.domain.score.CriterionType.DESCRIPTION_CRITERION;
import static com.idealista.domain.score.CriterionType.PICTURES_CRITERION;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.stereotype.Component;

@Component
public class ScoreCriterionFactory {
	
	public ScoreCriterion getCriterion(CriterionType criterionType) throws NoSuchBeanDefinitionException {
		if(criterionType.equals(PICTURES_CRITERION)) {
			return new PicturesCriterion();
		} else if (criterionType.equals(DESCRIPTION_CRITERION)) {
			return new DescriptionCriterion();
		} else if (criterionType.equals(COMPLETED_CRITERION)){
			return new CompletedCriterion();
		} else {
			throw new NoSuchBeanDefinitionException(criterionType.toString());
		}
	}

}
