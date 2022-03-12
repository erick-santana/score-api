package com.serasa.score.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.serasa.score.constants.Constants.INSUFFICIENT;
import static com.serasa.score.constants.Constants.RECOMMENDED;
import static com.serasa.score.factory.Factory.buildPerson;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class ScoreAdapterTest {

    @InjectMocks
    private ScoreAdapter scoreAdapter;

    @Test
    void toPersonResponse() {
        var toPersonResponse = scoreAdapter.toPersonResponse(buildPerson());

        assertThat(toPersonResponse).isNotNull();
        assertThat(toPersonResponse.getScoreDescription()).isEqualTo(RECOMMENDED);
    }

    @Test
    void toPersonLocationResponse() {
        var person = buildPerson();
        person.setScore(0);

        var toPersonLocationResponse = scoreAdapter.toPersonLocationResponse(person);

        assertThat(toPersonLocationResponse).isNotNull();
        assertThat(toPersonLocationResponse.getScoreDescription()).isEqualTo(INSUFFICIENT);
    }
}