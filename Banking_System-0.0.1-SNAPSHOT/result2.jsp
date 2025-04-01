<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.AccountDetails" %>
<%@ page import="com.example.Transaction" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking System Result</title>
</head>
<body>
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
            <table border="1">
                <tr>
                    <th>AccountNumber</th>
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
                out.println("No transactions found.");
            }
        %>
    <%
        } else {
            out.println("No account details available.");
        }
    %>

    <form action="<%= request.getContextPath() %>/home.jsp" method="get">
        <p><input type="submit" value="Back to Main Menu"></p>
    </form>
</body>
</html>