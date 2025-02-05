package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.UserBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.UserDAOImpl;
import com.assignment.abcfactory.entity.User;
import com.assignment.abcfactory.model.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBoImpl implements UserBo {
    UserDAOImpl userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USER);

    @Override
    public ArrayList<UserDto> getAll() throws SQLException {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        ArrayList<User> users = userDAO.getAll();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setUser_name(user.getUser_name());
            userDto.setPassword(user.getPassword());
            userDtos.add(userDto);
        }
        return userDtos;

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
        return null;
    }
    @Override
    public  boolean save(UserDto userDTO) throws SQLException {
       return userDAO.save(new User(userDTO.getUser_name(),userDTO.getPassword()));

    }

    @Override
    public  boolean update(UserDto userDTO) throws SQLException {
       return userDAO.update(new User(userDTO.getUser_name(),userDTO.getPassword()));
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
