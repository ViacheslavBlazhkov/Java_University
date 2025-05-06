package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions;

public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException() {
        super("Invalid transaction type");
    }
}
