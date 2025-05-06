package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.Advices;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.InvalidClientInfoException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InvalidClientInfoAdvice {
    @ResponseBody
    @ExceptionHandler(InvalidClientInfoException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String invalidClientInfo(InvalidClientInfoException ex) {
        return ex.getMessage();
    }
}
