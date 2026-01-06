package com.splitwise.backend.common.exception;

public class BusinessException extends RuntimeException{

    private final StandardResponseCode errorCode;

    public BusinessException(StandardResponseCode errorCode, String message){
        super(message);
        this.errorCode = errorCode;
    }

    public StandardResponseCode getErrorCode(){
        return errorCode;
    }

}
