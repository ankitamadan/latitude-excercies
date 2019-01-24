package com.latitude.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidStockPricesException extends Exception{

    private static final long serialVersionUID = 1L;

    public InvalidStockPricesException(String message){
        super(message);
    }

}
