package com.assignment.abcfactory.dao;

import java.sql.SQLException;

public interface JoinCrudDAO<T> extends SuperDAO {
    boolean save(T Dto)throws SQLException,ClassNotFoundException;
    boolean update(T Dto)throws SQLException,ClassNotFoundException;
}
