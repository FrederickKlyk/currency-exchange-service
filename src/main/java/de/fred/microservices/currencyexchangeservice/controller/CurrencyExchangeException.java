package de.fred.microservices.currencyexchangeservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyExchangeException extends RuntimeException {

    public CurrencyExchangeException(String message) {
        super(message);
    }
}
