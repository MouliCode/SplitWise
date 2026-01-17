package com.splitwise.backend.common.dto.response;

import com.splitwise.backend.common.exception.StandardResponseCode;

public record ApiResponse<T>(
		boolean		success,
		T 			data,
		Object	 	error
) {
   
   
   public static <T> ApiResponse<T> success (T data) {
	  return new ApiResponse<> (true, data, null);
   }
   
   public static <T> ApiResponse<T> failure (StandardResponseCode code, String message) {
	  return new ApiResponse<> (false, null, new ApiError (code, message));
   }
   
   public static <T> ApiResponse<T> failure (Object errors) {
	  return new ApiResponse<> (false, null, errors);
   }
   
   public boolean isSuccess () {
	  return success;
   }
   
   public T getData () {
	  return data;
   }

}
