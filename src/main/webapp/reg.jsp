<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <style>
        /* Full Page Background */
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: url('https://cdn.vuram.com/cc7dc8ee59a670eb6d7ad1625a0674c3.jpg');
            background-size: cover;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Form Container Styles */
        .form-container {
            background: rgba(255, 255, 255, 0.9); /* Slightly more opaque white background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            margin: auto; /* Centering the container */
        }

        form {
            display: flex;
            flex-direction: column;
        }

        label {
            margin-top: 10px;
            color: #4A6274; /* Steel Blue */
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        input[type="submit"] {
            margin-top: 20px;
            padding: 10px;
            color: white;
            background-color: #2980b9; /* Darker Blue */
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        input[type="submit"]:hover {
            background-color: #3498db; /* Lighter Blue */
        }

        .error-message {
            color: red;
        }

        .success-message {
            color: green;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Register</h2>
        <form action="UserRegisterServlet" method="post">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>

            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>

            <label for="place">Place:</label>
            <input type="text" id="place" name="place" required>

            <label for="area">Area:</label>
            <input type="text" id="area" name="area" required>

            <label for="district">District:</label>
            <input type="text" id="district" name="district" required>

            <label for="state">State:</label>
            <input type="text" id="state" name="state" required>

            <label for="gender">Gender:</label>
            <input type="text" id="gender" name="gender" required>

            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>

            <input type="submit" value="Register">
        </form>
    </div>
</body>
</html>
