package com.gamg.contaclist.exceptions;

public class ContactNotFoundException extends Throwable {
    public ContactNotFoundException(String usuarioNoEncontrado) {
        super(usuarioNoEncontrado);
    }
}
