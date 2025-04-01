<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banking System</title>
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
    
    <form action="<%= request.getContextPath() %>/BankingServlet" method="POST" onsubmit="return validateForm(this);">
        <input type="hidden" name="action" value="createAccount">
        <h2>Create Account</h2>
        <p>Account Number: <input type="text" name="accountNumber"></p>
        <p>Name: <input type="text" name="name"></p>
        <p>Initial Deposit: <input type="text" name="initialDeposit"></p>
        <p><input type="submit" value="Create Account"></p>
    </form>
    
</body>
</html>