package com.splitwise.backend.common.exception;

import com.splitwise.backend.common.dto.response.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Void> handleBusiness(BusinessException ex){
        return ApiResponse.error(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Void> handleValidation(MethodArgumentNotValidException ex){
        return ApiResponse.error(StandardResponseCode.VALIDATION_ERROR,
                StandardResponseCode.VALIDATION_ERROR.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Void> handleGeneric(Exception ex){
        return ApiResponse.error(StandardResponseCode.INTERNAL_ERROR, StandardResponseCode.INTERNAL_ERROR.getMessage());
    }
}
