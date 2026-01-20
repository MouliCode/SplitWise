package com.splitwise.backend.common.exception;

import com.splitwise.backend.common.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                                 errors.put(error.getField(), error.getDefaultMessage())
                );
        
        return ResponseEntity
                       .status(HttpStatus.BAD_REQUEST)
                       .body(ApiResponse.failure(errors));
    }
    
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(
            BusinessException ex
    ) {
        return ResponseEntity
                       .status(HttpStatus.BAD_REQUEST)
                       .body(ApiResponse.failure(ex.getErrorCode(), ex.getMessage()));
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex
    ) {
        
        ex.printStackTrace ();
        return ResponseEntity
                       .status(HttpStatus.INTERNAL_SERVER_ERROR)
                       .body(ApiResponse.failure(
                               StandardResponseCode.INTERNAL_ERROR,
                               StandardResponseCode.INTERNAL_ERROR.getMessage()
                       ));
    }
}
