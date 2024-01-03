package com.innter.msdesigngarmentmaterial.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InternalServerErrorTextil extends RuntimeException {
    private String code;
    private HttpStatus status;

    public InternalServerErrorTextil(String code, HttpStatus status, String message) {

        super(message);
        this.code = code;
        this.status = status;
    }
}
