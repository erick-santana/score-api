package com.serasa.score.repository;

import com.serasa.score.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoreRepository extends JpaRepository<Person, Integer> {
}
