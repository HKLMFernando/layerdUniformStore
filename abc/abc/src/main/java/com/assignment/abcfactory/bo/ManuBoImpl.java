package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.dao.custom.impl.ManuDAOImpl;
import com.assignment.abcfactory.model.ManuDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.dao.CrudUtil.execute;

public class ManuBoImpl implements ManuBo {
    ManuDAOImpl manuDAO = new ManuDAOImpl();

    @Override
    public String getNextId() throws SQLException {
        return manuDAO.getNextId();

    }

    @Override
    public ManuDto search(String id) throws SQLException, ClassNotFoundException {
      return manuDAO.search(id);
    }

    @Override
    public  OrderDto findByOrderId(String selectedOrderId) throws SQLException {
       return manuDAO.findByOrderId(selectedOrderId);
    }
    @Override
    public ArrayList<ManuDto> getAll() throws SQLException {
       return manuDAO.getAll();
    }
    @Override
    public boolean isOrderalredyAdded(String orderId) throws SQLException {
       return manuDAO.isOrderalredyAdded(orderId);
    }
    @Override
    public boolean save(ManuDto manuDto) throws SQLException {
       return manuDAO.save(manuDto);
    }
    @Override
    public boolean delete(String manuId) throws SQLException {
        return manuDAO.delete(manuId);
    }
    @Override
    public boolean update(ManuDto manuDto) throws SQLException {
       return manuDAO.update(manuDto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return manuDAO.exist(id);
    }
}
