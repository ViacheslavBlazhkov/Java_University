package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(long clientId) {
        super("Client " + clientId + " has not enough money");
    }
}
