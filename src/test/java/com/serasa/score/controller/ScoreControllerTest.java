package com.serasa.score.controller;

import com.serasa.score.controller.response.PersonLocationResponse;
import com.serasa.score.controller.response.PersonResponse;
import com.serasa.score.exception.PersonNotFoundException;
import com.serasa.score.model.Person;
import com.serasa.score.service.ScoreService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ScoreControllerTest {

    @InjectMocks
    private ScoreController scoreController;

    @Mock
    private ScoreService scoreService;

    @Test
    void testSavePersonSuccess() {
        var person = new Person();

        when(scoreService.save(person))
                .thenReturn(person);

        ResponseEntity<Void> save = scoreController.save(person);

        assertThat(save).isNotNull();
        assertThat(save.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(save.getBody()).isNull();
    }

    @Test
    void testSavePersonError() {
        var person = new Person();

        when(scoreService.save(person)).thenReturn(null);

        var save = scoreController.save(person);

        assertThat(save).isNotNull();
        assertThat(save.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(save.getBody()).isNull();
    }

    @Test
    void listAllSuccess() {
        var people = List.of(new PersonLocationResponse());

        when(scoreService.listAll()).thenReturn(people);

        var listAll = scoreController.listAll();

        assertThat(listAll).isNotNull();
        assertThat(listAll.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(listAll.getBody()).isNotNull();
    }

    @Test
    void listAllError() {
        List<PersonLocationResponse> people = List.of();

        when(scoreService.listAll()).thenReturn(people);

        var listAll = scoreController.listAll();

        assertThat(listAll).isNotNull();
        assertThat(listAll.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(listAll.getBody()).isNull();
    }

    @Test
    void findByIdSuccess() {
        var person = PersonResponse.builder().age(25).build();

        when(scoreService.findById(anyInt())).thenReturn(person);

        var findById = scoreController.findById(1);

        assertThat(findById).isNotNull();
        assertThat(findById.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(findById.getBody()).isNotNull();
        assertThat(findById.getBody().getAge()).isEqualTo(25);
    }

    @Test
    void findByIdError() {
        when(scoreService.findById(anyInt())).thenThrow(new PersonNotFoundException());

        assertThrows(PersonNotFoundException.class, () -> scoreController.findById(1));
    }
}