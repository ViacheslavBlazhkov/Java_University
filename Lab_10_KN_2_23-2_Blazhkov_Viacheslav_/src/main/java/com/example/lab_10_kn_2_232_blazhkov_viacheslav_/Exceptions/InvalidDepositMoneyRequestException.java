package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions;

import java.util.List;

public class InvalidDepositMoneyRequestException extends RuntimeException {
    public InvalidDepositMoneyRequestException(List<String> errors) {
        super("Errors: " + String.join(", ", errors));
    }
}
