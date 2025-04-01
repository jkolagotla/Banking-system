<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to banking system!</title>
</head>
<body>
    <h1>Welcome to banking system!</h1>
    <h2>Menu</h2>
    <a href="<%= request.getContextPath() %>/createAccount.jsp">Create Account</a><br>
    <a href="<%= request.getContextPath() %>/deposit.jsp">Deposit</a><br>
    <a href="<%= request.getContextPath() %>/withdrawl.jsp">Withdraw</a><br>
    <a href="<%= request.getContextPath() %>/view.jsp">View Account</a><br>
    <a href="<%= request.getContextPath() %>/index.jsp">Reports</a><br>
</body>
</html>