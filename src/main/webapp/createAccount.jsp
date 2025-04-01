<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking System</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-image: url('https://cdn.vuram.com/cc7dc8ee59a670eb6d7ad1625a0674c3.jpg'); /* Path to your background image */
            background-size: cover; /* Cover the whole screen */
            background-position: center;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333; /* Default text color */
        }

        h2 {
            color: #2980b9;
            text-align: center;
        }

        /* Form Styles */
        .form-container {
            background: rgba(255, 255, 255, 0.9); /* Slightly transparent white background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        .form-container p {
            margin: 10px 0;
        }

        .form-container label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        .form-container input[type="text"],
        .form-container input[type="number"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container input[type="submit"] {
            background-color: #2980b9;
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
            transition: background-color 0.3s ease;
        }

        .form-container input[type="submit"]:hover {
            background-color: #3498db;
        }
    </style>
    <script type="text/javascript">
        function validateForm(form) {
            for (let input of form) {
                if (input.type === "text" && input.value === "") {
                    alert(input.name + " must be filled out.");
                    return false;
                }
            }
            return true;
        }
    </script>
</head>
<body>
    <div class="form-container">
        <form action="<%= request.getContextPath() %>/BankingServlet" method="POST" onsubmit="return validateForm(this);">
            <input type="hidden" name="action" value="createAccount">
            <h2>Create Account</h2>
            <p>
                <label for="accountNumber">Account Number:</label>
                <input type="number" id="accountNumber" name="accountNumber" required>
            </p>
            <p>
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
            </p>
            <p>
                <label for="initialDeposit">Initial Deposit:</label>
                <input type="number" id="initialDeposit" name="initialDeposit" required>
            </p>
            <p>
                <input type="submit" value="Create Account">
            </p>
        </form>
    </div>
</body>
</html>
