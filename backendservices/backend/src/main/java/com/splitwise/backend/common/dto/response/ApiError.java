package com.splitwise.backend.common.dto.response;

import com.splitwise.backend.common.exception.StandardResponseCode;

public record ApiError(StandardResponseCode code, String message) {

}
