package com.innter.msdesigngarmentmaterial.exceptions;

import org.springframework.http.HttpStatus;

public class NoSuchFileExceptionTextil extends NotFoundTextil{
    private String code;
    private HttpStatus status;

    public NoSuchFileExceptionTextil(String code, HttpStatus status, String message) {

        super(code, status, message);
        this.code = code;
        this.status = status;
    }
}
