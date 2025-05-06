package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions;

public class InvalidClientInfoException extends RuntimeException {
    public InvalidClientInfoException(Iterable<String> errors) {
        super("Invalid client info: " + String.join(", ", errors));
    }
}
