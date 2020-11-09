package com.luizfrra.stockSim.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class QuoteNotExistException extends AbstractGeralException {
    public QuoteNotExistException(String message, Object requestData) {
        super(message, requestData);
    }
}
