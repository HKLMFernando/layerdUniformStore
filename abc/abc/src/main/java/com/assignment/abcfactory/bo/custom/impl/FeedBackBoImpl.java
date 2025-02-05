package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.FeedBackBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.FeedBackModelDAOImpl;
import com.assignment.abcfactory.entity.FeedBack;
import com.assignment.abcfactory.model.FeedBackDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FeedBackBoImpl implements FeedBackBo {
    FeedBackModelDAOImpl feedBackModelDAO =(FeedBackModelDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.FEEDBACK);


    @Override
    public boolean save(FeedBackDto feedbackDTO) throws SQLException {
        return feedBackModelDAO.save(new FeedBack(feedbackDTO.getFeed_back_Id() , feedbackDTO.getFeed_back() ,feedbackDTO.getCust_Id()));
    }
    @Override
    public boolean update(FeedBackDto feedbackDTO) throws SQLException, ClassNotFoundException {
     return feedBackModelDAO.update(new FeedBack(feedbackDTO.getFeed_back_Id() , feedbackDTO.getFeed_back() ,feedbackDTO.getCust_Id()));
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return feedBackModelDAO.exist(id);
    }
   @Override
    public ArrayList<FeedBackDto> getAll() throws SQLException {
        ArrayList<FeedBackDto> feedBackDtos = new ArrayList<>();
        ArrayList<FeedBack> feedBacks = feedBackModelDAO.getAll();
        for (FeedBack feedBack : feedBacks) {
            FeedBackDto feedBackDto = new FeedBackDto();
            feedBackDto.setFeed_back_Id(feedBack.getFeed_back_Id());
            feedBackDto.setFeed_back(feedBack.getFeed_back());
            feedBackDto.setCust_Id(feedBack.getCust_Id());
            feedBackDtos.add(feedBackDto);
        }
       return feedBackDtos;
    }
    @Override
    public String getNextId() throws SQLException {

        return feedBackModelDAO.getNextId();
    }

    @Override
    public FeedBackDto search(String id) throws SQLException, ClassNotFoundException {
       return null;
    }
    @Override
    public boolean delete(String feedbackID) throws SQLException {
       return feedBackModelDAO.delete(feedbackID);
    }
}
