package com.serasa.score.controller;

import com.serasa.score.controller.response.PersonLocationResponse;
import com.serasa.score.controller.response.PersonResponse;
import com.serasa.score.model.Person;
import com.serasa.score.service.ScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("score")
@Slf4j
@RequiredArgsConstructor
public class ScoreController {

    private final ScoreService scoreService;

    @PostMapping("/pessoa")
    public ResponseEntity<Void> save(@RequestBody @Valid Person person) {
        log.info("Requisição Recebida para cadastrar pessoa");

        var personFound = scoreService.save(person);

        if (personFound != null) {
            log.info("Pessoa cadastrada com sucesso.");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        log.info("Pessoa já cadastrada");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/pessoa")
    public ResponseEntity<List<PersonLocationResponse>> listAll() {
        log.info("Requisição Recebida para listar pessoas");

        var people = scoreService.listAll();

        if (CollectionUtils.isEmpty(people)) {
            log.warn("Nenhum dado cadastrado");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        log.info("Lista de pessoas encontrada com sucesso");
        return ResponseEntity.ok(people);
    }

    @GetMapping("/pessoa/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable Integer id) {
        log.info("Requisição Recebida para buscar pessoa com o id [{}]", id);

        return ResponseEntity.ok(scoreService.findById(id));
    }
}
