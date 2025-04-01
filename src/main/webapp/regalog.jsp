<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Banking System!</title>
    <style>
        /* General Styles */
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

        .container {
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
            text-align: center;
        }

        h1, h2 {
            color: #4A6274; /* Steel Blue */
        }

        .option-container {
            margin: 20px 0;
        }

        .option-container a {
            display: block;
            color: #ffffff;
            background-color: #2980b9;
            padding: 10px 20px;
            margin: 5px 0;
            text-decoration: none;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .option-container a:hover {
            background-color: #3498db;
        }

        /* Form Styles */
        .form-container {
            background: rgba(255, 255, 255, 0.8); /* Semi-transparent white background */
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
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
    <div class="container">
        <h1>Welcome to Banking System!</h1>
        <h2>Choose an option:</h2>
        <div class="option-container">
            <a href="login.jsp">Login</a>
            <a href="reg.jsp">Register</a>
        </div>
    </div>
</body>
</html>
