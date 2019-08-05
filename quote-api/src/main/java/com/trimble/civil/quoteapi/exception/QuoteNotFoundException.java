package com.trimble.civil.quoteapi.exception;

public class QuoteNotFoundException extends RuntimeException{

    public QuoteNotFoundException(Long id) {
        super("Could not find  " + id);
    }

}
