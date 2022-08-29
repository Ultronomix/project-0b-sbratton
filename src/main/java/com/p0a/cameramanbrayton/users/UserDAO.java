package com.p0a.cameramanbrayton.users;

import com.p0a.cameramanbrayton.workers.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// DAO = Data Access Object
public class UserDAO {

    public List<User> getAllUsers() {

        String sql = "SELECT au.id, au.given_name, au.surname, au.email, au.username, au.role_id, ur.role " +
                "FROM app_users au " +
                "JOIN user_roles ur " +
                "ON au.role_id = ur.id";

        List<User> allUsers = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

            // JDBC Statement objects are subject to SQL Injections
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setGivenName(resultSet.getString("given_name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                user.setUsername(resultSet.getString("username"));
                //user.setPassword(resultSet.getString("password")); // done for security purpose
                user.setRole(new Role(resultSet.getString("role_id"), resultSet.getString("role")));
                allUsers.add(user);
            }

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database!");
            e.printStackTrace();
        }

        return allUsers;

    }

    public String save(User user) {

        String sql = "INSERT INTO app_users (given_name, surname, email, username, password, role_id) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {

            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[] {"id"});
            preparedStatement.setString(1, user.getGivenName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());

            preparedStatement.executeUpdate();

            connection.commit();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            user.setId(resultSet.getString("id"));

        } catch (SQLException e) {
            System.err.println("Something went wrong when communicating with the database!");
            e.printStackTrace();
        }

        return user.getId();

    }

}
