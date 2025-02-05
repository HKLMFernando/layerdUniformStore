package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.bo.SuperBo;
import com.assignment.abcfactory.model.FeedBackDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FeedBackBo extends SuperBo {

     boolean save(FeedBackDto feedbackDTO) throws SQLException;
     boolean update(FeedBackDto dto) throws SQLException, ClassNotFoundException;
     boolean exist(String id) throws SQLException, ClassNotFoundException;
     ArrayList<FeedBackDto> getAll() throws SQLException;
     String getNextId() throws SQLException ;
     FeedBackDto search(String id) throws SQLException, ClassNotFoundException;
     boolean delete(String feedbackID) throws SQLException ;
}
