package com.luizfrra.stockSim.EntitiesDomain.Exception;

import com.luizfrra.stockSim.Exceptions.AbstractGeralException;
import com.luizfrra.stockSim.Exceptions.DataAlreadyExistException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

public class ErrorInfo {

    public final String url;

    public final String message;

    public final long timeStamp;

    public final String httpMethod;

    public Object object;

    private ErrorInfo(HttpServletRequest req, Exception ex) {
        this.url = req.getRequestURI();
        this.httpMethod = req.getMethod();
        this.message = ex.getMessage();
        this.timeStamp = new Date().getTime();
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public static ErrorInfo create(HttpServletRequest req, Exception ex) {
        ErrorInfo errorInfo = new ErrorInfo(req, ex);

        if(ex instanceof AbstractGeralException)
            errorInfo.setObject(((AbstractGeralException) ex).requestData);

        return errorInfo;
    }
}
