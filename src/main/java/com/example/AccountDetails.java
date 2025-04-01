package com.example;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountDetails {
    private String accountNumber;
    private String accountHolderName;
    private double balance;
    private List<Transaction> transactions = new ArrayList<>();
    private final Lock balanceLock = new ReentrantLock();

    public AccountDetails(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    public boolean deposit(double amount) {
        balanceLock.lock();
        try {
            if (amount > 0) {
                balance += amount;
                transactions.add(new Transaction(accountNumber, "DEPOSIT", amount, "Deposit made", new Timestamp(System.currentTimeMillis())));
                return true;
            } else {
                return false;
            }
        } finally {
            balanceLock.unlock();
        }
    }

    public boolean withdraw(double amount) {
        balanceLock.lock();
        try {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                transactions.add(new Transaction(accountNumber, "WITHDRAW", amount, "Withdrawal made", new Timestamp(System.currentTimeMillis())));
                return true;
            } else {
                return false;
            }
        } finally {
            balanceLock.unlock();
        }
    }

    public double getBalance() {
        balanceLock.lock();
        try {
            return balance;
        } finally {
            balanceLock.unlock();
        }
    }
    
    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountDetails() {
        return String.format("Account Number: %s, Account Holder: %s, Balance: %.2f", accountNumber, accountHolderName, balance);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}