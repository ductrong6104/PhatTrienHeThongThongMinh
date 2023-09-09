package com.schoolproject.Drugstore.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;

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

    @ExceptionHandler(CannotDeleteDataException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage cannotDeleteDataException(Exception ex, WebRequest request) {
        return new ErrorMessage(ErrorMessage.CANNOT_DELETE, "Không thể xóa đối tượng");
    }

}
