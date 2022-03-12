package com.serasa.score.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super();
    }
}
