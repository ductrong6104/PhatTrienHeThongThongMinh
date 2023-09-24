package com.schoolproject.Drugstore.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

    public static final int NOT_FOUND = 10001;
    public static final int CANNOT_CREATE = 10002;
    public static final int CANNOT_EDIT = 10003;
    public static final int CANNOT_DELETE = 10004;
    
    public static final int REQUEST_BODY_EMPTY = 10005;
    public static final int NOTFOUND_PRODUCTTYPE = 10006;


    private int statusCode;
    private String message;
}
