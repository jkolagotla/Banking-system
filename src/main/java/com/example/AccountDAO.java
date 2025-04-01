package com.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDAO {
	//Account Methods
    public void createAccount(AccountDetails account) throws SQLException {
        String sql = "INSERT INTO accounts (account_number, account_holder_name, balance) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountNumber());
            stmt.setString(2, account.getAccountHolderName());
            stmt.setDouble(3, account.getBalance());
            stmt.executeUpdate();
        }
    }

    public AccountDetails getAccount(String accountNumber) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AccountDetails(
                        rs.getString("account_number"),
                        rs.getString("account_holder_name"),
                        rs.getDouble("balance")
                    );
                }
            }
        }
        return null;
    }

    public void updateAccount(AccountDetails account) throws SQLException {
        String sql = "UPDATE accounts SET account_holder_name = ?, balance = ? WHERE account_number = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getAccountHolderName());
            stmt.setDouble(2, account.getBalance());
            stmt.setString(3, account.getAccountNumber());
            stmt.executeUpdate();
        }
    }

    public void deleteAccount(String accountNumber) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_number = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            stmt.executeUpdate();
        }
    }

    public List<AccountDetails> getAllAccounts() throws SQLException {
        List<AccountDetails> accounts = new ArrayList<>();
        String sql = "SELECT * FROM accounts";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                accounts.add(new AccountDetails(
                    rs.getString("account_number"),
                    rs.getString("account_holder_name"),
                    rs.getDouble("balance")
                ));
            }
        }
        return accounts;
    }
}