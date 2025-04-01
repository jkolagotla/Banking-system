package com.example;

import java.sql.*;
import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    public void createTransaction(Transaction transaction) throws SQLException {
        String sql = "INSERT INTO transactions (account_number, transaction_type, amount, description, timestamp) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, transaction.getAccountNumber());
            stmt.setString(2, transaction.getType());
            stmt.setDouble(3, transaction.getAmount());
            stmt.setString(4, transaction.getDescription());
            stmt.setTimestamp(5, transaction.getTimestamp());
            stmt.executeUpdate();
        }
    }

    public List<Transaction> getTransactionsByAccount(String accountNumber) throws SQLException {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE account_number = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, accountNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    transactions.add(new Transaction(
                        rs.getString("account_number"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getString("description"),
                        rs.getTimestamp("timestamp")
                    ));
                }
            }
        }
        return transactions;
    }
 
}