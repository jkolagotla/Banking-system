package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountDetailsTest {
    private AccountDetails account;

    @BeforeEach
    void setUp() {
        account = new AccountDetails("123456", "John Doe", 1000);
    }
    
    @Test
    void testAccountNumber() {
    	assertEquals("123456", account.getAccountNumber(), "AccountNumber should be 123456");
    }
    
    @Test
    void testAccountHolderName() {
    	assertEquals("John Doe", account.getAccountHolderName(),"AccountHolderName should be John Doe");
    }
    
    @Test
    void testDeposit() {
        account.deposit(500);
        assertEquals(1500, account.getBalance(), "Balance should be 1500 after depositing 500");
    }
    
    @Test
    void testInvalidDeposit() {
    	boolean success = account.deposit(-100);
    	assertFalse(success,"Deposit should fail for negative amount");
    	assertEquals(1000,account.getBalance(),"Balance should remain same after inavalid deposit");
    }
    
    @Test
    void testWithdraw() {
        boolean success = account.withdraw(400);
        assertTrue(success, "Withdrawal should be successful");
        assertEquals(600, account.getBalance(), "Balance should be 600 after withdrawing 400");
    }
    
    @Test
    void testInvalidWithdraw() {
    	boolean success = account.withdraw(-100);
    	assertFalse(success,"Withdrawl should fail for negative amount");
    	assertEquals(1000,account.getBalance(),"Balance should remain same after inavalid withdrawl");
    }

    @Test
    void testWithdrawInsufficientFunds() {
        boolean success = account.withdraw(2000);
        assertFalse(success, "Withdrawal should fail due to insufficient funds");
        assertEquals(1000, account.getBalance(), "Balance should remain 1000 after failed withdrawal");
    }

    @Test
    void testGetAccountDetails() {
        String details = account.getAccountDetails();
        assertTrue(details.contains("123456"));
        assertTrue(details.contains("John Doe"));
        assertTrue(details.contains("1000"));
    }
}