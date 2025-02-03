package com.assignment.abcfactory.model;

import com.assignment.abcfactory.dto.ItemDto;
import com.assignment.abcfactory.dto.UserDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.util.CrudUtil.execute;

public class UserModel {

    public static ArrayList<UserDto> getAllItems() throws SQLException {
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

    public static boolean deleteItem(String userName) throws SQLException {
        return CrudUtil.execute("delete from user where user_name=?", userName);
    }

    public static boolean saveUser(UserDto userDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into user values (?,?)",
                userDTO.getUser_name(),
                userDTO.getPassword()
        );

    }


    public static boolean updateUser(UserDto userDTO) throws SQLException {
        return false;
    }

    public static UserDto authenticateUser(String username, String password) {
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
