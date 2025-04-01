<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking System - View Account</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
             background-image: url('https://cdn.vuram.com/cc7dc8ee59a670eb6d7ad1625a0674c3.jpg'); 
            padding: 20px;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        h2 {
            color: #2980b9;
            text-align: center;
        }

        /* Form Styles */
        .form-container {
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .form-container p {
            margin: 10px 0;
        }

        .form-container input[type="text"] {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
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
            <input type="hidden" name="action" value="viewAccount">
            <h2>View Account</h2>
            <p>
                <label for="accountNumber">Account Number:</label>
                <input type="number" id="accountNumber" name="accountNumber">
            </p>
            <p>
                <input type="submit" value="View Account">
            </p>
        </form>
    </div>
</body>
</html>