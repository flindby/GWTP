package com.gwtplatform.carstore.shared.dispatch;

public class Response {
    protected String exceptionMessage;

    public Response() {
    }

    public Response(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
