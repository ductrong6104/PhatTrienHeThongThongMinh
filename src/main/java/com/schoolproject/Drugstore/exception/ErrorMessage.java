package com.schoolproject.Drugstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    public static final int NOT_FOUND = 10001;
    public static final int CANNOT_CREATE = 10002;
    public static final int CANNOT_DELETE = 10003;

    private int statusCode;
    private String message;
}
