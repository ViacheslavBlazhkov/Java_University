package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.Advices;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.NotEnoughMoneyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotEnoughMoneyAdvice {
    @ResponseBody
    @ExceptionHandler(NotEnoughMoneyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String clientNotFoundHandler(NotEnoughMoneyException ex) {
        return ex.getMessage();
    }
}
