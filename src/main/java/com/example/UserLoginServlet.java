package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            if (validateUser(username, password)) {
                // Login successful
                response.sendRedirect("home.jsp");
            } else {
                // Login failed
                response.sendRedirect("login.jsp?error=Invalid username or password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ServletException("Database error", e);
        }
    }

    private boolean validateUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM users1 WHERE username = ? AND password = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password); // Consider using hashed passwords
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If a record is found, login is valid
            }
        }
    }
}
