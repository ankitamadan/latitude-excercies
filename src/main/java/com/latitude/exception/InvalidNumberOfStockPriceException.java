package com.latitude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class InvalidNumberOfStockPriceException extends Exception {

    private static final long serialVersionUID = 1L;

    public InvalidNumberOfStockPriceException(String message){
        super(message);
    }
}
