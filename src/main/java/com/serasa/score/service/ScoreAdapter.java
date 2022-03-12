package com.serasa.score.service;

import com.serasa.score.controller.response.PersonLocationResponse;
import com.serasa.score.controller.response.PersonResponse;
import com.serasa.score.model.Person;
import org.springframework.stereotype.Component;

import static com.serasa.score.constants.Constants.ACCEPTABLE;
import static com.serasa.score.constants.Constants.INSUFFICIENT;
import static com.serasa.score.constants.Constants.RECOMMENDED;
import static com.serasa.score.constants.Constants.UNACCEPTABLE;

@Component
public class ScoreAdapter {

    public PersonResponse toPersonResponse(Person person) {
        return PersonResponse.builder()
                .name(person.getName())
                .phone(person.getPhone())
                .age(person.getAge())
                .scoreDescription(getScoreDescription(person.getScore()))
                .build();
    }

    public PersonLocationResponse toPersonLocationResponse(Person person) {
        return PersonLocationResponse.builder()
                .name(person.getName())
                .city(person.getPhone())
                .district(person.getDistrict())
                .scoreDescription(getScoreDescription(person.getScore()))
                .build();
    }

    private String getScoreDescription(Integer score) {
        if (isBetween(score, 0, 200)) {
            return INSUFFICIENT;
        } else if (isBetween(score, 201, 500)) {
            return UNACCEPTABLE;
        } else if (isBetween(score, 501, 700)) {
            return ACCEPTABLE;
        } else if (isBetween(score, 701, 1000)) {
            return RECOMMENDED;
        } else {
            return "";
        }
    }

    private boolean isBetween(int x, int lower, int upper) {
        return lower <= x && x <= upper;
    }
}
