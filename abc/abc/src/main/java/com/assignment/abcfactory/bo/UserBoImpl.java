package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.dao.custom.impl.UserDAOImpl;
import com.assignment.abcfactory.model.UserDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {
    UserDAOImpl userDAO = new UserDAOImpl();

    @Override
    public ArrayList<UserDto> getAll() throws SQLException {
        return userDAO.getAll();

    }
    @Override
    public  boolean delete(String userName) throws SQLException {
        return userDAO.delete(userName);
    }

    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return userDAO.getNextId();
    }

    @Override
    public UserDto search(String id) throws SQLException, ClassNotFoundException {
        return userDAO.search(id);
    }
    @Override
    public  boolean save(UserDto userDTO) throws SQLException {
       return userDAO.save(userDTO);

    }

    @Override
    public  boolean update(UserDto userDTO) throws SQLException {
       return userDAO.update(userDTO);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return userDAO.exist(id);
    }
    @Override
    public  UserDto authenticateUser(String username, String password) {
        return userDAO.authenticateUser(username, password);

    }
}
