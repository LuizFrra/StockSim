package com.luizfrra.stockSim.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DataAlreadyExistException extends AbstractGeralException {

    public DataAlreadyExistException(String message, Object requestData) {
        super(message, requestData);
    }
}
