package com.serasa.score.factory;

import com.serasa.score.controller.response.PersonLocationResponse;
import com.serasa.score.controller.response.PersonResponse;
import com.serasa.score.model.Person;

import static com.serasa.score.constants.Constants.RECOMMENDED;

public class Factory {

    public static Person buildPerson() {
        return Person.builder()
                .name("Erick")
                .phone("11233334444")
                .age(25)
                .city("Aracaju")
                .district("SE")
                .score(1000)
                .build();
    }

    public static PersonResponse buildPersonResponse() {
        return PersonResponse.builder()
                .name("Erick")
                .age(25)
                .phone("11233334444")
                .scoreDescription(RECOMMENDED)
                .build();
    }

    public static PersonLocationResponse buildPersonLocationResponse() {
        return PersonLocationResponse.builder()
                .name("Erick")
                .city("Aracaju")
                .district("SE")
                .scoreDescription(RECOMMENDED)
                .build();
    }
}
