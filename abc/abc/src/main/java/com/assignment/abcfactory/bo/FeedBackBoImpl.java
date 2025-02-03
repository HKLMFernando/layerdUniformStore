package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.dao.custom.impl.FeedBackModelDAOImpl;
import com.assignment.abcfactory.model.FeedBackDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedBackBoImpl implements FeedBackBo {
    FeedBackModelDAOImpl feedBackModelDAO = new FeedBackModelDAOImpl();
    @Override
    public boolean save(FeedBackDto feedbackDTO) throws SQLException {
        return feedBackModelDAO.save(feedbackDTO);
    }
    @Override
    public boolean update(FeedBackDto dto) throws SQLException, ClassNotFoundException {
     return feedBackModelDAO.update(dto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return feedBackModelDAO.exist(id);
    }
   @Override
    public ArrayList<FeedBackDto> getAll() throws SQLException {
       return feedBackModelDAO.getAll();
    }
    @Override
    public String getNextId() throws SQLException {

        return feedBackModelDAO.getNextId();
    }

    @Override
    public FeedBackDto search(String id) throws SQLException, ClassNotFoundException {
       return feedBackModelDAO.search(id);
    }
    @Override
    public boolean delete(String feedbackID) throws SQLException {
       return feedBackModelDAO.delete(feedbackID);
    }
}
