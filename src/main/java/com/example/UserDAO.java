package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private static final String INSERT_USER_SQL = "INSERT INTO users1 (first_name, last_name, place, area, district, state, gender, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void createUser(User user) throws SQLException {
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
            
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPlace());
            preparedStatement.setString(4, user.getArea());
            preparedStatement.setString(5, user.getDistrict());
            preparedStatement.setString(6, user.getState());
            preparedStatement.setString(7, user.getGender());
            preparedStatement.setString(8, user.getUsername());
            preparedStatement.setString(9, user.getPassword());

            preparedStatement.executeUpdate();
        }
    }
}
