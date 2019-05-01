package com.microservices.transactions.excepetion;

public class TransactionException extends RuntimeException {
    public TransactionException(String message) {
        super(message);
    }
}