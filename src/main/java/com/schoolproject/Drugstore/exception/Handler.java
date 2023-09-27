package com.schoolproject.Drugstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.exception.customeException.NotFoundProductTypeException;
import com.schoolproject.Drugstore.exception.customeException.RequestBodyEmptyException;
import com.schoolproject.Drugstore.exception.customeException.RequestNotFoundException;

@RestControllerAdvice
public class Handler {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage dataNotFoundException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.NOT_FOUND, "Đối tượng không tồn tại");
    }

    @ExceptionHandler(CannotCreateDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage cannotCreateDataException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.CANNOT_CREATE, "Không thể tạo đối tượng");
    }

    @ExceptionHandler(CannotEditDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage cannotEditDataException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.CANNOT_CREATE, "Không thể sửa đối tượng");
    }

    @ExceptionHandler(CannotDeleteDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage cannotDeleteDataException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.CANNOT_DELETE, "Không thể xóa đối tượng");
    }

    @ExceptionHandler(RequestBodyEmptyException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage requestBodyEmptyException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.REQUEST_BODY_EMPTY, "Request body is empty");
    }

    @ExceptionHandler(NotFoundProductTypeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage notFoundProductTypeException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.NOTFOUND_PRODUCTTYPE, "Not found product type");
    }

    @ExceptionHandler(RequestNotFoundException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage requestNotFoundException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.REQUEST_NOTFOUND, "Request not found");
    }
}
