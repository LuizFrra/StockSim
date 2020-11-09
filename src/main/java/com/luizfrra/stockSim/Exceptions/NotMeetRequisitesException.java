package com.luizfrra.stockSim.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class NotMeetRequisitesException extends AbstractGeralException {
    public NotMeetRequisitesException(String message, Object requestData) {
        super(message, requestData);
    }
}
