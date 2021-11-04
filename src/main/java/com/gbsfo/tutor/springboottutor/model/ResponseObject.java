package com.gbsfo.tutor.springboottutor.model;

public class ResponseObject {
    private int code;
    private String message;
    
    public ResponseObject() {}
    
    public ResponseObject(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
