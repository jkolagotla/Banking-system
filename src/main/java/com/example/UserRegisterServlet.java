package com.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet implements Serializable {
    private static final long serialVersionUID = 1L; // Added serialVersionUID

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String place = request.getParameter("place");
        String area = request.getParameter("area");
        String district = request.getParameter("district");
        String state = request.getParameter("state");
        String gender = request.getParameter("gender");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPlace(place);
        newUser.setArea(area);
        newUser.setDistrict(district);
        newUser.setState(state);
        newUser.setGender(gender);
        newUser.setUsername(username);
        newUser.setPassword(password);

        try {
            userDAO.createUser(newUser); // Attempt to create user in the database
            response.sendRedirect("home.jsp"); // Redirect to home page if successful
        } catch (SQLException e) {
            throw new ServletException("Database access error", e); // Handle SQL exceptions properly
        }
    }
}
