package com.gamg.contaclist.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ContactServiceException extends RuntimeException {

    public ContactServiceException(String message) {
        super(message);
    }
}
