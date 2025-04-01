package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class AccountDAOTest {
    private AccountDAO accountDAO;
    private AccountDetails account;
    
    @BeforeEach
    public void setUp() throws SQLException {
        accountDAO = new AccountDAO();
        account = new AccountDetails("123456", "John Doe", 1000);
        accountDAO.createAccount(account);
    }
    
    @AfterEach
    public void tearDown() throws SQLException {
        accountDAO.deleteAccount("123456");
    }
    
    @Test
    public void testCreateAccount() throws SQLException {
        AccountDetails newAccount = new AccountDetails("789012", "Jane Doe", 2000);
        accountDAO.createAccount(newAccount);
        AccountDetails retrievedAccount = accountDAO.getAccount("789012");
        assertNotNull(retrievedAccount);
        assertEquals("Jane Doe", retrievedAccount.getAccountHolderName());
        assertEquals(2000, retrievedAccount.getBalance());
        accountDAO.deleteAccount("789012");
    }
    
    @Test
    public void testGetAccount() throws SQLException {
        AccountDetails retrievedAccount = accountDAO.getAccount("123456");
        assertNotNull(retrievedAccount);
        assertEquals("John Doe", retrievedAccount.getAccountHolderName());
        assertEquals(1000, retrievedAccount.getBalance());
    }
    
    @Test
    public void testUpdateAccount() throws SQLException {
        account.setBalance(2000);
        accountDAO.updateAccount(account);
        AccountDetails updatedAccount = accountDAO.getAccount("123456");
        assertEquals(2000, updatedAccount.getBalance());
    }
    
    @Test
    public void testDeleteAccount() throws SQLException {
        accountDAO.deleteAccount("123456");
        AccountDetails deletedAccount = accountDAO.getAccount("123456");
        assertNull(deletedAccount);
    }
    
    @Test
    public void testGetAllAccounts() throws SQLException {
        List<AccountDetails> accounts = accountDAO.getAllAccounts();
        assertNotNull(accounts);
        //assertTrue(accounts.size() > 0);
    }
}