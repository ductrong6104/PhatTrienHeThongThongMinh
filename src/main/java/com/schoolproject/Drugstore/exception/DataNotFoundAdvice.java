package com.schoolproject.Drugstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class DataNotFoundAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String dataNotFoundHandler(DataNotFoundException ex){
        return ex.getMessage();
    }
    // xu ly cac ngoai le: vi pham rang buoc khoa ngoai
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception ex, WebRequest request) {
        String details = ex.getLocalizedMessage();
        ErrorResponse error = new ErrorResponse("ApplicationConstants.SERVER_ERROR", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
