package com.lytuan99.projectIwarehouse.exceptions.supplierException;

import com.lytuan99.projectIwarehouse.exceptions.ApiError;
import com.lytuan99.projectIwarehouse.exceptions.commonException.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SupplierExceptionHandler {

    @ExceptionHandler(value = SupplierException.class)
    public ResponseEntity<ApiError> exception(SupplierException exceiption) {
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exceiption.getMessage(), "error");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

//    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    public ResponseEntity<ApiError> exception(MethodArgumentNotValidException exception) {
//        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Dau vao khong hop le", "error");
//        return new ResponseEntity<>(apiError, apiError.getStatus());
//    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError> exception(NotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, "Khong tim thay tai nguyen", "error");
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
