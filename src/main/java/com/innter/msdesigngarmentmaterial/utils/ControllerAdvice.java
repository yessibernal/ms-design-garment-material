package com.innter.msdesigngarmentmaterial.utils;

import com.innter.msdesigngarmentmaterial.exceptions.BadRequestTextil;
import com.innter.msdesigngarmentmaterial.exceptions.InternalServerErrorTextil;
import com.innter.msdesigngarmentmaterial.exceptions.NoSuchFileExceptionTextil;
import com.innter.msdesigngarmentmaterial.exceptions.NotFoundTextil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(value = BadRequestTextil.class)//Error 400
    public ResponseEntity<ErrorDto> badRequestTextil(BadRequestTextil er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }

    @ExceptionHandler(value = {NotFoundTextil.class, NoSuchFileExceptionTextil.class})//Error 404
    public ResponseEntity<ErrorDto> notFoundTextil(NotFoundTextil er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }

    @ExceptionHandler(value = InternalServerErrorTextil.class)//Error 500
    public ResponseEntity<ErrorDto> InternalServerErrorTextil(InternalServerErrorTextil er) {
        ErrorDto error = ErrorDto.builder().code(er.getCode()).message(er.getMessage()).build();
        return new ResponseEntity<>(error, er.getStatus());
    }
}
