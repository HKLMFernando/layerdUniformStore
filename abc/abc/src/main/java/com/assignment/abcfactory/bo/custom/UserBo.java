package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.model.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBo {
    ArrayList<UserDto> getAll() throws SQLException;
    boolean delete(String userName) throws SQLException;
    String getNextId() throws SQLException, ClassNotFoundException ;
    UserDto search(String id) throws SQLException, ClassNotFoundException ;
    boolean save(UserDto userDTO) throws SQLException ;
    boolean update(UserDto userDTO) throws SQLException ;
    boolean exist(String id) throws SQLException, ClassNotFoundException;
    UserDto authenticateUser(String username, String password) ;
}
