package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.itextpdf.text.DocumentException;

@WebServlet(name = "BankingServlet", urlPatterns=("/BankingServlet"))
public class BankingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final AccountDAO accountDAO = new AccountDAO();
    private static final TransactionDAO transactionDAO = new TransactionDAO();

    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("Test Servlet is working!");
    }*/
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String result = "";
        try {
            switch (action) {
                case "createAccount":
                    result = createAccount(request);
                    break;
                case "deposit":
                    result = deposit(request);
                    break;
                case "withdraw":
                    result = withdraw(request);
                    break;
                case "viewAccount":
                    result = viewAccount(request);
                    break;
                default:
                    result = "Invalid action.";
            }
        } catch (SQLException e) {
            result = "Database error: " + e.getMessage();
        } 
        
        request.setAttribute("result", result);
        RequestDispatcher dispatcher = request.getRequestDispatcher("result.jsp");
        dispatcher.forward(request, response);
    }

    private String createAccount(HttpServletRequest request) throws SQLException {
        String accountNumber = request.getParameter("accountNumber");
        String name = request.getParameter("name");
        double initialDeposit;

        try {
            initialDeposit = Double.parseDouble(request.getParameter("initialDeposit"));
        } catch (NumberFormatException e) {
            return "Initial Deposit must be a valid number.";
        }

        if (accountNumber == null || accountNumber.isEmpty() || name == null || name.isEmpty()) {
            return "Account number and name must not be empty.";
        }
        if (initialDeposit < 0) {
            return "Initial balance cannot be negative.";
        }
        
        AccountDetails existingAccount = accountDAO.getAccount(accountNumber);
        if (existingAccount != null) {
        	request.setAttribute("account", existingAccount);
            List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
            request.setAttribute("transactions", transactions);
            return "Account already exists.";
        }

        AccountDetails account = new AccountDetails(accountNumber, name, initialDeposit);
        accountDAO.createAccount(account);
        request.setAttribute("account", account);
        Transaction transaction = new Transaction(accountNumber, "DEPOSIT", initialDeposit, "Deposit made", new Timestamp(System.currentTimeMillis()));
        transactionDAO.createTransaction(transaction);
        List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
        request.setAttribute("transactions", transactions);
        return "Account created successfully!";
    }

    private String deposit(HttpServletRequest request) throws SQLException {
        String accountNumber = request.getParameter("accountNumber");
        double amount;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            return "Amount must be a valid number.";
        }
        
        AccountDetails account = accountDAO.getAccount(accountNumber);
        if (account != null && amount >= 0) {
            account.deposit(amount);
            Transaction transaction = new Transaction(accountNumber, "DEPOSIT", amount, "Deposit made", new Timestamp(System.currentTimeMillis()));
            transactionDAO.createTransaction(transaction);
            accountDAO.updateAccount(account);
            request.setAttribute("account", account);
            List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
            request.setAttribute("transactions", transactions);
            return "Deposit successful!";
        } else {
            return "Account not found or invalid deposit amount.";
        }
    }

    private String withdraw(HttpServletRequest request) throws SQLException {
        String accountNumber = request.getParameter("accountNumber");
        double amount;

        try {
            amount = Double.parseDouble(request.getParameter("amount"));
        } catch (NumberFormatException e) {
            return "Amount must be a valid number.";
        }

        AccountDetails account = accountDAO.getAccount(accountNumber);
        if (account != null && amount >= 0) {
            if (account.withdraw(amount)) {
                Transaction transaction = new Transaction(accountNumber, "WITHDRAW", amount, "Withdrawal made", new Timestamp(System.currentTimeMillis()));
                transactionDAO.createTransaction(transaction);
                accountDAO.updateAccount(account);
                request.setAttribute("account", account);
                List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
                request.setAttribute("transactions", transactions);
                return "Withdrawal successful!";
            } else {
                return "Insufficient funds.";
            }
        } else {
            return "Account not found or invalid withdrawal amount.";
        }
    }

    public String viewAccount(HttpServletRequest request) throws SQLException {
        String accountNumber = request.getParameter("accountNumber");

        AccountDetails account = accountDAO.getAccount(accountNumber);
        if (account != null) {
            request.setAttribute("account", account);
            List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountNumber);
            request.setAttribute("transactions", transactions);
            return "Account details retrieved.";
        } else {
            return "Account not found.";
        }
    }
    
   
}