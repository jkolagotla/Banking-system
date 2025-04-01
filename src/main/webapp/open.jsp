<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to Banking System!</title>
    <style>
        /* General Styles */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-position: center;
            background-repeat: no-repeat;
            color: white; /* Text color to stand out against the background */
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
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
        }

        .option-container a:hover {
            background-color: #3498db;
        }

        .form-container {
            margin: 20px 0;
        }

        .form-container form {
            display: none; /* Initially hide all forms */
        }

        .form-container form.active {
            display: block; /* Show active form */
        }

        .form-container input[type="text"], .form-container input[type="password"], .form-container select {
            width: calc(100% - 22px);
            padding: 10px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .form-container input[type="submit"] {
            background-color: #4A6274; /* Steel Blue */
            color: #ffffff;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            margin-top: 10px;
        }

        .form-container input[type="submit"]:hover {
            background-color: #2c3e50; /* Darker Steel Blue */
        }
    </style>
    <script type="text/javascript">
        function showForm(type, role) {
            document.getElementById('userOptions').style.display = 'none';
            document.getElementById('adminOptions').style.display = 'none';
            document.getElementById('userLoginForm').classList.remove('active');
            document.getElementById('userRegisterForm').classList.remove('active');
            document.getElementById('adminLoginForm').classList.remove('active');
            document.getElementById('adminRegisterForm').classList.remove('active');

            if (role === 'user') {
                document.getElementById('userOptions').style.display = 'block';
                if (type === 'login') {
                    document.getElementById('userLoginForm').classList.add('active');
                } else if (type === 'register') {
                    document.getElementById('userRegisterForm').classList.add('active');
                }
            } else if (role === 'admin') {
                document.getElementById('adminOptions').style.display = 'block';
                if (type === 'login') {
                    document.getElementById('adminLoginForm').classList.add('active');
                } else if (type === 'register') {
                    document.getElementById('adminRegisterForm').classList.add('active');
                }
            }
        }

        function showRoleOptions(role) {
            document.getElementById('initialOptions').style.display = 'none';
            if (role === 'user') {
                document.getElementById('userOptions').style.display = 'block';
            } else if (role === 'admin') {
                document.getElementById('adminOptions').style.display = 'block';
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>Welcome to Banking System!</h1>
        <div id="initialOptions" class="option-container">
            <a href="javascript:void(0);" onclick="showRoleOptions('user')">User</a>
            <a href="javascript:void(0);" onclick="showRoleOptions('admin')">Admin</a>
        </div>

        <div id="userOptions" class="option-container" style="display:none;">
            <a href="javascript:void(0);" onclick="showForm('login', 'user')">Login</a>
            <a href="javascript:void(0);" onclick="showForm('register', 'user')">Register</a>
        </div>

        <div id="adminOptions" class="option-container" style="display:none;">
            <a href="javascript:void(0);" onclick="showForm('login', 'admin')">Login</a>
            <a href="javascript:void(0);" onclick="showForm('register', 'admin')">Register</a>
        </div>

        <div class="form-container">
            <!-- User Login Form -->
            <form id="userLoginForm" action="userLoginServlet" method="POST">
                <h2>User Login</h2>
                <p>
                    <label for="userUsername">Username:</label>
                    <input type="text" id="userUsername" name="username" placeholder="Enter username">
                </p>
                <p>
                    <label for="userPassword">Password:</label>
                    <input type="password" id="userPassword" name="password" placeholder="Enter password">
                </p>
                <p>
                    <input type="submit" value="Login">
                </p>
            </form>

            <!-- User Registration Form -->
            <form id="userRegisterForm" action="userRegisterServlet" method="POST">
                <h2>User Registration</h2>
                <p>
                    <label for="userFirstName">First Name:</label>
                    <input type="text" id="userFirstName" name="firstName" placeholder="Enter first name">
                </p>
                <p>
                    <label for="userLastName">Last Name:</label>
                    <input type="text" id="userLastName" name="lastName" placeholder="Enter last name">
                </p>
                <p>
                    <label for="userPlace">Place:</label>
                    <input type="text" id="userPlace" name="place" placeholder="Enter place">
                </p>
                <p>
                    <label for="userArea">Area:</label>
                    <input type="text" id="userArea" name="area" placeholder="Enter area">
                </p>
                <p>
                    <label for="userDistrict">District:</label>
                    <input type="text" id="userDistrict" name="district" placeholder="Enter district">
                </p>
                <p>
                    <label for="userState">State:</label>
                    <input type="text" id="userState" name="state" placeholder="Enter state">
                </p>
                <p>
                    <label for="userGender">Gender:</label>
                    <select id="userGender" name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </p>
                <p>
                    <input type="submit" value="Register">
                </p>
            </form>

            <!-- Admin Login Form -->
            <form id="adminLoginForm" action="adminLoginServlet" method="POST">
                <h2>Admin Login</h2>
                <p>
                    <label for="adminUsername">Username:</label>
                    <input type="text" id="adminUsername" name="username" placeholder="Enter username">
                </p>
                <p>
                    <label for="adminPassword">Password:</label>
                    <input type="password" id="adminPassword" name="password" placeholder="Enter password">
                </p>
                <p>
                    <input type="submit" value="Login">
                </p>
            </form>

            <!-- Admin Registration Form -->
            <form id="adminRegisterForm" action="adminRegisterServlet" method="POST">
                <h2>Admin Registration</h2>
                <p>
                    <label for="adminFirstName">First Name:</label>
                    <input type="text" id="adminFirstName" name="firstName" placeholder="Enter first name">
                </p>
                <p>
                    <label for="adminLastName">Last Name:</label>
                    <input type="text" id="adminLastName" name="lastName" placeholder="Enter last name">
                </p>
                <p>
                    <label for="adminPlace">Place:</label>
                    <input type="text" id="adminPlace" name="place" placeholder="Enter place">
                </p>
                <p>
                    <label for="adminArea">Area:</label>
                    <input type="text" id="adminArea" name="area" placeholder="Enter area">
                </p>
                <p>
                    <label for="adminDistrict">District:</label>
                    <input type="text" id="adminDistrict" name="district" placeholder="Enter district">
                </p>
                <p>
                    <label for="adminState">State:</label>
                    <input type="text" id="adminState" name="state" placeholder="Enter state">
                </p>
                <p>
                    <label for="adminGender">Gender:</label>
                    <select id="adminGender" name="gender">
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                        <option value="other">Other</option>
                    </select>
                </p>
                <p>
                    <input type="submit" value="Register">
                </p>
            </form>
        </div>
    </div>
</body>
</html>