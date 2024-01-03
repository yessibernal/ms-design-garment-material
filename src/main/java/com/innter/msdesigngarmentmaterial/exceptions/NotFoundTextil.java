package com.innter.msdesigngarmentmaterial.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NotFoundTextil extends RuntimeException{
    private String code;
    private HttpStatus status;

    public NotFoundTextil(String code, HttpStatus status, String message) {

        super(message);
        this.code = code;
        this.status = status;
    }
}
