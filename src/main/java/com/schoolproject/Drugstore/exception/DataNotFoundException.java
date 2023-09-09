package com.schoolproject.Drugstore.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(Integer id, String nameObj){
        super("Not found " + nameObj.toLowerCase() + " has id = " + id);
    }
}
