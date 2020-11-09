package com.luizfrra.stockSim.Controllers.Exception;

import com.luizfrra.stockSim.EntitiesDomain.Exception.ErrorInfo;
import com.luizfrra.stockSim.Exceptions.DataAlreadyExistException;
import com.luizfrra.stockSim.Exceptions.NotMeetRequisitesException;
import com.luizfrra.stockSim.Exceptions.QuoteNotExistException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler({DataAlreadyExistException.class, ConstraintViolationException.class})
    public ResponseEntity<ErrorInfo> conflitException(HttpServletRequest req, Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.create(req, exception);
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({NotMeetRequisitesException.class})
    public ResponseEntity<ErrorInfo> notMeetRequisitesException(HttpServletRequest req, Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.create(req, exception);
        return new ResponseEntity<>(errorInfo, HttpStatus.PRECONDITION_FAILED);
    }

    @ExceptionHandler({QuoteNotExistException.class})
    public ResponseEntity<ErrorInfo> quoteNotExistException(HttpServletRequest req, Exception exception) {
        ErrorInfo errorInfo = ErrorInfo.create(req, exception);
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

}
