package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(long clientId) {
        super("Could not find client " + clientId);
    }
}
