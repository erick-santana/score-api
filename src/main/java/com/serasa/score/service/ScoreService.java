package com.serasa.score.service;

import com.serasa.score.controller.response.PersonLocationResponse;
import com.serasa.score.controller.response.PersonResponse;
import com.serasa.score.exception.PersonNotFoundException;
import com.serasa.score.model.Person;
import com.serasa.score.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;
    private final ScoreAdapter scoreAdapter;

    @Transactional
    public Person save(Person person){
        try {
            scoreRepository.save(person);
            return person;
        } catch (Exception e) {
            log.error("Não foi possível efetuar o cadastro");
            return null;
        }
    }

    public List<PersonLocationResponse> listAll() {
        var people = scoreRepository.findAll();

        return CollectionUtils.isEmpty(people)
                ? List.of()
                : people.stream()
                    .map(scoreAdapter::toPersonLocationResponse)
                    .collect(Collectors.toList());
    }

    public PersonResponse findById(Integer id){
        var person = scoreRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Nenhuma pessoa encontrada com o id [{}]", id);
                    return new PersonNotFoundException();
                });

        log.info("Pessoa com o id [{}] encontrada", id);
        return scoreAdapter.toPersonResponse(person);
    }
}
