package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.UserDAO;
import com.assignment.abcfactory.entity.User;
import com.assignment.abcfactory.model.UserDto;
import com.assignment.abcfactory.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public  ArrayList<User> getAll() throws SQLException {
        // Execute SQL query to get all item IDs
        ArrayList<User> users = new ArrayList<>();

        // Database query to get all items
        ResultSet resultSet = CrudUtil.execute("SELECT user_name,password FROM user");

        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            String password = resultSet.getString("password");
            users.add(new User( userName,password));
        }
        return users;
    }

    public  boolean delete(String userName) throws SQLException {
        return CrudUtil.execute("delete from user where user_name=?", userName);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public User search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean save(User userDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into user values (?,?)",
                userDTO.getUser_name(),
                userDTO.getPassword()
        );

    }


    public  boolean update(User userDTO) throws SQLException {
        return CrudUtil.execute (
                "update user set password=? where user_name=?",
                userDTO.getPassword(),
                userDTO.getUser_name()
        );

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  UserDto authenticateUser(String username, String password) {
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM user WHERE user_name = ? AND password = ?", username, password);
            if (resultSet.next()) {
                String dbUsername = resultSet.getString("user_name");
                String dbPassword = resultSet.getString("password");
                return new UserDto(dbUsername, dbPassword);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;

    }
}
