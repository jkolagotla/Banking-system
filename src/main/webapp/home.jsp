<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Banking System!</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://cdn.vuram.com/cc7dc8ee59a670eb6d7ad1625a0674c3.jpg'); 
            background-size: cover; /* Cover the entire viewport */
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* Full height of viewport */
        }

        .container {
            max-width: 600px;
            width: 100%;
            background: rgba(255, 255, 255, 0.9); /* Slightly transparent white background */
            padding: 20px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            border-radius: 8px;
            text-align: center;
        }

        h1 {
            color: #2c3e50;
            margin-bottom: 20px;
        }

        h2 {
            color: #2980b9;
            margin-top: 0;
        }

        /* Navigation Menu */
        .menu {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        .menu a {
            display: block;
            color: #ffffff;
            background-color: #2980b9;
            padding: 10px 20px;
            margin: 5px 0;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .menu a:hover {
            background-color: #3498db;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Banking System!</h1>
        <h2>Menu</h2>
        <ul class="menu">
            <li><a href="<%= request.getContextPath() %>/createAccount.jsp">Create Account</a></li>
            <li><a href="<%= request.getContextPath() %>/deposit.jsp">Deposit</a></li>
            <li><a href="<%= request.getContextPath() %>/withdraw.jsp">Withdraw</a></li>
            <li><a href="<%= request.getContextPath() %>/view.jsp">View Account</a></li>
        </ul>
    </div>
</body>
</html>
