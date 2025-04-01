<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.AccountDetails" %>
<%@ page import="com.example.Transaction" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking System Result</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
             background-image: url('https://cdn.vuram.com/cc7dc8ee59a670eb6d7ad1625a0674c3.jpg'); 
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        .container {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 800px;
        }

        h1, h2 {
            color: #2980b9;
            text-align: center;
        }

        p {
            color: #333333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #cccccc;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #2980b9;
            color: #ffffff;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .back-button {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #2980b9;
            color: white;
            text-decoration: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .back-button:hover {
            background-color: #3498db;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Result</h1>
        <p><%= (request.getAttribute("result") != null) ? request.getAttribute("result") : "" %></p>

        <h2>Account Details</h2>
        <%
            AccountDetails account = (AccountDetails) request.getAttribute("account");
            if (account != null) {
        %>
            <p>Account Number: <%= account.getAccountNumber() %></p>
            <p>Account Holder Name: <%= account.getAccountHolderName() %></p>
            <p>Balance: <%= account.getBalance() %></p>

            <h2>Transactions</h2>
            <%
                List<Transaction> transactions = (List<Transaction>) request.getAttribute("transactions");
                if (transactions != null && !transactions.isEmpty()) {
            %>
                <table>
                    <tr>
                        <th>Account Number</th>
                        <th>Type</th>
                        <th>Amount</th>
                        <th>Description</th>
                        <th>Timestamp</th>
                    </tr>
                    <%
                        for (Transaction transaction : transactions) {
                    %>
                        <tr>
                            <td><%= transaction.getAccountNumber() %></td>
                            <td><%= transaction.getType() %></td>
                            <td><%= transaction.getAmount() %></td>
                            <td><%= transaction.getDescription() %></td>
                            <td><%= transaction.getTimestamp() %></td>
                        </tr>
                    <%
                        }
                    %>
                </table>
            <%
                } else {
                    out.println("<p>No transactions found.</p>");
                }
            %>
        <%
            } else {
                out.println("<p>No account details available.</p>");
            }
        %>

        <form action="<%= request.getContextPath() %>/home.jsp" method="get">
            <p><input type="submit" class="back-button" value="Back to Main Menu"></p>
        </form>
    </div>
</body>
</html>