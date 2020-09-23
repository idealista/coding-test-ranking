package com.idealista;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:/application.yml")
public class ApplicationPropertiesTest {

    @Value("${score.min}")
    Integer minScore;
    @Value("${score.max}")
    Integer maxScore;
    @Value("${score.min-relevant-score}")
    Integer minRelevantScore;

    @Value("${score.rules.picture.empty-points}")
    Integer noPicturePoints;
    @Value("${score.rules.picture.sd-points}")
    Integer sdPicturePoints;
    @Value("${score.rules.picture.hd-points}")
    Integer hdPicturePoints;

    @Value("${score.rules.description.points}")
    Integer points;

    @Value("${score.rules.description-typology.flat.min-points}")
    Integer flatMinPoints;
    @Value("${score.rules.description-typology.flat.min-words}")
    Integer flatMinWords;
    @Value("${score.rules.description-typology.flat.full-points}")
    Integer flatFullPoints;
    @Value("${score.rules.description-typology.flat.full-words}")
    Integer flatFullWords;
    @Value("${score.rules.description-typology.chalet.full-points}")
    Integer chaletFullPoints;
    @Value("${score.rules.description-typology.chalet.full-words}")
    Integer chaletFullWords;

    @Value("${score.rules.relevant-words.points}")
    Integer relevantWordsPoints;
    @Value("${score.rules.relevant-words.words}")
    List<String> relevantWords;

    @Value("${score.rules.completed.points}")
    Integer completedPoints;

    @Test
    public void shouldHaveAllProperties() {
        assertThat(minScore).isNotNull().isEqualTo(0);
        assertThat(maxScore).isNotNull().isEqualTo(100);
        assertThat(minRelevantScore).isNotNull().isEqualTo(40);

        assertThat(noPicturePoints).isNotNull().isEqualTo(-10);
        assertThat(sdPicturePoints).isNotNull().isEqualTo(10);
        assertThat(hdPicturePoints).isNotNull().isEqualTo(20);

        assertThat(flatMinPoints).isNotNull().isEqualTo(10);
        assertThat(flatMinWords).isNotNull().isEqualTo(20);
        assertThat(flatFullPoints).isNotNull().isEqualTo(30);
        assertThat(flatFullWords).isNotNull().isEqualTo(50);

        assertThat(chaletFullPoints).isNotNull().isEqualTo(20);
        assertThat(chaletFullWords).isNotNull().isEqualTo(50);

        assertThat(relevantWordsPoints).isNotNull().isEqualTo(5);
        assertThat(relevantWords).isNotEmpty().containsExactly("luminoso", "nuevo", "céntrico", "reformado", "ático");

        assertThat(completedPoints).isNotNull().isEqualTo(40);
    }

}
