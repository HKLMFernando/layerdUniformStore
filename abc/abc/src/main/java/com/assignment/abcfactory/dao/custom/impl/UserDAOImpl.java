package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.UserDAO;
import com.assignment.abcfactory.dto.UserDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    public  ArrayList<UserDto> getAll() throws SQLException {
        // Execute SQL query to get all item IDs
        ArrayList<UserDto> users = new ArrayList<>();

        // Database query to get all items
        ResultSet resultSet = CrudUtil.execute("SELECT user_name,password FROM user");

        while (resultSet.next()) {
            String userName = resultSet.getString("user_name");
            String password = resultSet.getString("password");
            users.add(new UserDto( userName,password));
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
    public UserDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean save(UserDto userDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into user values (?,?)",
                userDTO.getUser_name(),
                userDTO.getPassword()
        );

    }


    public  boolean update(UserDto userDTO) throws SQLException {
        return false;
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
