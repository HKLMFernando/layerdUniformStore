package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.FeedBackDAO;
import com.assignment.abcfactory.model.FeedBackDto;
import com.assignment.abcfactory.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FeedBackModelDAOImpl implements FeedBackDAO {
    public boolean save(FeedBackDto feedbackDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into feed_back values (?,?,?)",
                feedbackDTO.getFeed_back_Id(),
                feedbackDTO.getFeed_back(),
                feedbackDTO.getCust_Id()
        );

    }

    @Override
    public boolean update(FeedBackDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public ArrayList<FeedBackDto> getAll() throws SQLException {
        // Execute SQL query to get all item IDs
        ArrayList<FeedBackDto> feedbacks = new ArrayList<>();

        // Database query to get all items
        ResultSet resultSet = CrudUtil.execute("SELECT feed_back_id,feed_back,cust_id FROM feed_back");

        while (resultSet.next()) {
            String FeedBackId = resultSet.getString("feed_back_id");
            String FeedBack = resultSet.getString("feed_back");
            String CustId = resultSet.getString("cust_id");

            feedbacks.add(new FeedBackDto( FeedBackId,FeedBack,CustId));
        }
        return feedbacks;

    }

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select feed_back_id from feed_back order by feed_back_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("F%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "F001";

    }

    @Override
    public FeedBackDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean delete(String feedbackID) throws SQLException {
        return CrudUtil.execute("delete from feed_back where feed_back_id = ?", feedbackID);
    }
}
