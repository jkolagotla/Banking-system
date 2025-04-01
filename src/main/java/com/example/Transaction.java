package com.example;

import java.sql.Timestamp;

public class Transaction {
    private String accountNumber;
    private String type;
    private double amount;
    private String description;
    private Timestamp timestamp;

    public Transaction(String accountNumber, String type, double amount, String description, Timestamp timestamp) {
        this.accountNumber = accountNumber;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.timestamp = timestamp;
    }
    
    public String getAccountNumber() {
    	return this.accountNumber;
    }
    
    public String getType() {
    	return this.type;
    }
    
    public double getAmount() {
    	return this.amount;
    }
    
    public String getDescription() {
    	return this.description;
    }
    
    public Timestamp getTimestamp() {
    	return this.timestamp;
    }
    
    @Override
    public String toString() {
        return String.format("Transaction: [%s] Type: %s, Amount: %.2f, Description: %s, Timestamp: %s",
                accountNumber, type, amount, description, timestamp.toString());
    }
}