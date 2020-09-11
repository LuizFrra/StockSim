package com.luizfrra.stockSim.Responses.Commons;

public class ObjectResponse {
    public String message;

    public Object data;

    public ObjectResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
