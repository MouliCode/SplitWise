package com.splitwise.backend.common.dto.response;

import com.splitwise.backend.common.exception.StandardResponseCode;

public class ApiError {

    private StandardResponseCode code;
    private String message;

    public ApiError(StandardResponseCode code, String message){
        this.code = code;
        this.message = message;
    }

    public StandardResponseCode getCode(){
        return code;
    }

    public String getMessage(){
        return message;
    }

}
