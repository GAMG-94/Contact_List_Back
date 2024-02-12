package com.gamg.contaclist.exceptions;

public class ContactNotFoundById extends RuntimeException{

    public ContactNotFoundById (Long id, String message) {
        super(message);
    }
}
