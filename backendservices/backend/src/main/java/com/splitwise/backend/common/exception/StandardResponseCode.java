package com.splitwise.backend.common.exception;

public enum StandardResponseCode {

     SUCCESS("success"),
     VALIDATION_ERROR("invalid request data"),
     INTERNAL_ERROR("something went wrong"),
     FAILURE("failure");

     private final String message;

     StandardResponseCode(){
         this.message = name().toLowerCase();
     }

     StandardResponseCode(String message){
        this.message = message;
     }

     @Override
    public String toString(){
         return message;
     }

    public String getMessage() {
         return message;
    }
}
