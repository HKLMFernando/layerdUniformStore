package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.dao.custom.impl.ItemDAOImpl;
import com.assignment.abcfactory.model.ItemDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {
    ItemDAOImpl itemDAO = new ItemDAOImpl();
    @Override
    public  boolean save(ItemDto itemDto) throws SQLException {
        return itemDAO.save(itemDto);

    }
    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return itemDAO.exist(id);
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return itemDAO.getNextId();
    }

    @Override
    public ItemDto search(String id) throws SQLException, ClassNotFoundException {
        return itemDAO.search(id);
    }
    @Override
    public ItemDto findById(String selectedItemId) throws SQLException {
       return itemDAO.findById(selectedItemId);
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return itemDAO.getAllIds();

    }
    @Override
    public  boolean update(ItemDto itemDto) throws SQLException {
        return itemDAO.update(itemDto);
    }
    @Override
    public  boolean delete(String itemId) throws SQLException {
       return itemDAO.delete(itemId);
    }

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException {
       return itemDAO.getAll();
    }
}
