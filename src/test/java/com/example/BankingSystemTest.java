package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.sql.Timestamp;

public class BankingSystemTest {
    private AccountDetails account;
    private ArrayList<Transaction> transactionHistory;

    @BeforeEach
    public void setUp() {
        account = new AccountDetails("N190263", "Mounika", 1000.0);
        transactionHistory = new ArrayList<>();
    }

    @Test
    public void testInitialBalance() {
        assertEquals(1000.0, account.getBalance(), "Initial balance should be 1000.0");
    }

    @Test
    public void testDeposit() {
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance(), "Balance should be 1500.0 after depositing 500.0");
    }

    @Test
    public void testWithdraw() {
        account.withdraw(200.0);
        assertEquals(800.0, account.getBalance(), "Balance should be 800.0 after withdrawing 200.0");
    }

    @Test
    public void testOverWithdraw() {
        account.withdraw(1200.0);
        assertEquals(1000.0, account.getBalance(), "Balance should remain 1000.0 after trying to withdraw 1200.0");
    }

    @Test
    public void testNegativeDeposit() {
        account.deposit(-100.0);
        assertEquals(1000.0, account.getBalance(), "Balance should remain 1000.0 after trying to deposit -100.0");
    }

    @Test
    public void testNegativeWithdraw() {
        account.withdraw(-100.0);
        assertEquals(1000.0, account.getBalance(), "Balance should remain 1000.0 after trying to withdraw -100.0");
    }

    @Test
    public void testAccountDetails() {
        String expectedDetails = "Account Number: N190263, Account Holder: Mounika, Balance: 1000.00";
        assertEquals(expectedDetails, account.getAccountDetails(), "Account details should match the expected output");
    } 

    @Test
    public void testMultipleDeposits() {
        account.deposit(500.0);
        account.deposit(200.0);
        assertEquals(1700.0, account.getBalance(), "Balance should reflect multiple deposits");
    }

    @Test
    public void testMultipleWithdrawals() {
        account.withdraw(200.0);
        account.withdraw(300.0);
        assertEquals(500.0, account.getBalance(), "Balance should reflect multiple withdrawals");
    }

    @Test
    public void testZeroDeposit() {
        account.deposit(0.0);
        assertEquals(1000.0, account.getBalance(), "Balance should not change for zero deposit");
    }
    @Test
    public void testZeroWithdrawal() {
        account.withdraw(0.0);
        assertEquals(1000.0, account.getBalance(), "Balance should not change for zero withdrawal");
    }
    @Test
    public void testWithdrawalAfterMultipleDeposits() {
        account.deposit(500.0);
        account.deposit(200.0);
        account.withdraw(1000.0);
        assertEquals(700.0, account.getBalance(), "Balance should reflect correct amount after multiple deposits and a withdrawal");
    }
    @Test
    public void testTransactionHistory() {
        account.deposit(200.00);
        transactionHistory.add(new Transaction(account.getAccountNumber(),"Deposit", 200.00, "Deposit made", new Timestamp(System.currentTimeMillis()) ));
        account.withdraw(100.00);
        transactionHistory.add(new Transaction(account.getAccountNumber(), "Withdrawal", 100.00, "Withdrawal made", new Timestamp(System.currentTimeMillis())));

        
        assertEquals(2, transactionHistory.size());
        assertEquals("Deposit", transactionHistory.get(0).getType());
        assertEquals(200.00, transactionHistory.get(0).getAmount());
        assertNotNull(transactionHistory.get(0).getTimestamp(), "First transaction should have a timestamp");
        
        assertEquals("Withdrawal", transactionHistory.get(1).getType());
        assertEquals(100.00, transactionHistory.get(1).getAmount());
        assertNotNull(transactionHistory.get(1).getTimestamp(), "Second transaction should have a timestamp");
    }
    
    @Test
    public void testLoginSuccess() {
        String input = "admin\npassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        boolean result = BankingSystem.login(scanner);
        assertTrue(result);
    }
    @Test
    public void testLoginFailure() {
        String input = "admin\nwrongpassword\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);
        boolean result = BankingSystem.login(scanner);
        assertFalse(result);
    }
   
}