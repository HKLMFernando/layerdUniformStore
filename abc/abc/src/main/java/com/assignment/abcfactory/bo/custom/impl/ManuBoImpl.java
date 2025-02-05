package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.ManuBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.ManuDAO;
import com.assignment.abcfactory.dao.custom.impl.ManuDAOImpl;
import com.assignment.abcfactory.entity.Manu;
import com.assignment.abcfactory.model.ManuDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ManuBoImpl implements ManuBo {
    ManuDAO manuDAO = (ManuDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.MANU);

    @Override
    public String getNextId() throws SQLException {
        try {
            return manuDAO.getNextId();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ManuDto search(String id) throws SQLException, ClassNotFoundException {
      return null;
    }

    @Override
    public  OrderDto findByOrderId(String selectedOrderId) throws SQLException {
       return null;
    }
    @Override
    public ArrayList<ManuDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ManuDto> manuDtos = new ArrayList<>();
        System.out.println(manuDAO.getAll());
        ArrayList<Manu> manu = manuDAO.getAll();
        for (Manu m : manu) {
            ManuDto mDto = new ManuDto();
            mDto.setManufacturing_id( m.getManufacturing_id());
            mDto.setOrder_id( m.getOrder_id());
            mDto.setProsses_details( m.getProsses_details());
            manuDtos.add(mDto);
        }

       return manuDtos;
    }
    @Override
    public boolean isOrderalredyAdded(String orderId) throws SQLException {
       return manuDAO.isOrderalredyAdded(orderId);
    }
    @Override
    public boolean save(ManuDto manuDto) throws SQLException, ClassNotFoundException {
       return manuDAO.save (new Manu(manuDto.getManufacturing_id(),manuDto.getProsses_details(),manuDto.getOrder_id()));
    }
    @Override
    public boolean delete(String manuId) throws SQLException, ClassNotFoundException {
        return manuDAO.delete(manuId);
    }
    @Override
    public boolean update(ManuDto manuDto) throws SQLException, ClassNotFoundException {
       return manuDAO.update(new Manu(manuDto.getManufacturing_id(),manuDto.getProsses_details(),manuDto.getOrder_id()));

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return manuDAO.exist(id);
    }
}
