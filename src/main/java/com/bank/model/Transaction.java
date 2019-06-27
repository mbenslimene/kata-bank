package com.bank.model;

import java.time.LocalDateTime;

public class Transaction {

    public enum TransactionType {
        DEPOSIT, WITHDRAWAL
    }

    private TransactionType type;
    private float amount;
    private float initialBalance;
    private LocalDateTime creationDate;

    public Transaction(TransactionType type, float amount, float initialBalance) {
        this.type = type;
        this.amount = amount;
        this.initialBalance = initialBalance;
        // set creation date
        creationDate = LocalDateTime.now();
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(float initialBalance) {
        this.initialBalance = initialBalance;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
