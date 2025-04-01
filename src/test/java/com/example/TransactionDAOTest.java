package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class TransactionDAOTest {
    private TransactionDAO transactionDAO;
    private AccountDAO accountDAO;
    private AccountDetails account;
    
    @BeforeEach
    public void setUp() throws SQLException {
        transactionDAO = new TransactionDAO();
        accountDAO = new AccountDAO();
        account = new AccountDetails("123456", "John Doe", 1000);
        accountDAO.createAccount(account);
    }
    
    @AfterEach
    public void tearDown() throws SQLException {
        accountDAO.deleteAccount("123456");
    }
    
    @Test
    public void testCreateTransaction() throws SQLException {
        Transaction transaction = new Transaction("123456", "DEPOSIT", 500, "Deposit made", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(transaction);
        List<Transaction> transactions = transactionDAO.getTransactionsByAccount("123456");
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals("DEPOSIT", transactions.get(0).getType());
    }
    
    @Test
    public void testGetTransactionsByAccount() throws SQLException {
        Transaction transaction1 = new Transaction("123456", "DEPOSIT", 500, "Deposit made", new Timestamp(System.currentTimeMillis()));
        Transaction transaction2 = new Transaction("123456", "WITHDRAW", 300, "Withdrawal made", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(transaction1);
        transactionDAO.createTransaction(transaction2);
        List<Transaction> transactions = transactionDAO.getTransactionsByAccount("123456");
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertEquals("DEPOSIT", transactions.get(0).getType());
        assertEquals("WITHDRAW", transactions.get(1).getType());
    }
}