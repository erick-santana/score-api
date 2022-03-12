package com.serasa.score.service;

import com.serasa.score.exception.PersonNotFoundException;
import com.serasa.score.model.Person;
import com.serasa.score.repository.ScoreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static com.serasa.score.constants.Constants.RECOMMENDED;
import static com.serasa.score.factory.Factory.buildPerson;
import static com.serasa.score.factory.Factory.buildPersonLocationResponse;
import static com.serasa.score.factory.Factory.buildPersonResponse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ScoreServiceTest {

    @InjectMocks
    private ScoreService scoreService;

    @Mock
    private ScoreRepository scoreRepository;

    @Mock
    private ScoreAdapter scoreAdapter;

    @Test
    void testSaveSuccess() {
        var person = Person.builder().district("SE").build();

        when(scoreRepository.save(person)).thenReturn(person);

        var save = scoreService.save(person);

        assertThat(save).isNotNull();
        assertThat(save.getDistrict()).isEqualTo("SE");
    }

    @Test
    void testSaveError() {
        var person = new Person();

        when(scoreRepository.save(person)).thenThrow(new RuntimeException());

        var save = scoreService.save(person);

        assertThat(save).isNull();
    }

    @Test
    void listAllSuccess() {
        when(scoreRepository.findAll()).thenReturn(List.of(buildPerson()));
        when(scoreAdapter.toPersonLocationResponse(any(Person.class)))
                .thenReturn(buildPersonLocationResponse());

        var findAll = scoreService.listAll();

        assertThat(findAll).isNotNull();
        assertThat(findAll.get(0).getDistrict()).isEqualTo("SE");
    }

    @Test
    void listAllError() {
        when(scoreRepository.findAll()).thenReturn(List.of());

        var findAll = scoreService.listAll();

        assertThat(findAll).isNotNull();
        assertThat(findAll).isEmpty();
    }

    @Test
    void findByIdSuccess() {
        when(scoreRepository.findById(anyInt())).thenReturn(Optional.of(buildPerson()));
        when(scoreAdapter.toPersonResponse(any(Person.class))).thenReturn(buildPersonResponse());

        var findById = scoreService.findById(1);

        assertThat(findById).isNotNull();
        assertThat(findById.getScoreDescription()).isEqualTo(RECOMMENDED);
    }

    @Test
    void findByIdError() {
        when(scoreRepository.findById(anyInt())).thenThrow(new PersonNotFoundException());

        assertThrows(PersonNotFoundException.class, () -> scoreService.findById(1));
    }
}