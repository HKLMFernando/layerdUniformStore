package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.ItemBO;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.ItemDAOImpl;
import com.assignment.abcfactory.entity.FeedBack;
import com.assignment.abcfactory.entity.Item;
import com.assignment.abcfactory.model.FeedBackDto;
import com.assignment.abcfactory.model.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBoImpl implements ItemBO {
    ItemDAOImpl itemDAO =(ItemDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ITEM);


    @Override
    public  boolean save(ItemDto itemDto) throws SQLException {
        return itemDAO.save(new Item(itemDto.getItem_id() ,itemDto.getItem_name()));

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
        return null;
    }
    @Override
    public ItemDto findById(String selectedItemId) throws SQLException {
       return null;
    }

    @Override
    public ArrayList<String> getAllIds() throws SQLException {
        return itemDAO.getAllIds();

    }
    @Override
    public  boolean update(ItemDto itemDto) throws SQLException {
        return itemDAO.update(new Item(itemDto.getItem_id() ,itemDto.getItem_name()));
    }
    @Override
    public  boolean delete(String itemId) throws SQLException {
       return itemDAO.delete(itemId);
    }

    @Override
    public ArrayList<ItemDto> getAll() throws SQLException {
        ArrayList<ItemDto> itemDtos = new ArrayList<>();
        ArrayList<Item> items = itemDAO.getAll();
        for (Item item : items) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItem_id(item.getItem_id());
            itemDto.setItem_name(item.getItem_name());
            itemDtos.add(itemDto);

        }
        return itemDtos;

    }
}
