package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.ItemDAO;
import com.assignment.abcfactory.dto.ItemDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModelDAO implements ItemDAO {
    


    public  boolean save(ItemDto itemDto) throws SQLException {
        return CrudUtil.execute(
                "insert into item values (?,?)",
                itemDto.getItem_id(),
                itemDto.getItem_name()
        );
    }



    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return "";
    }

    @Override
    public ItemDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public ItemDto findById(String selectedItemId) throws SQLException {
        // Execute SQL query to find the item by ID
        ResultSet rst = CrudUtil.execute("select * from item where item_id=?", selectedItemId);

        // If the item is found, create an ItemDTO object with the retrieved data
        if (rst.next()) {
            return new ItemDto(
                    rst.getString("item_id"),
                    rst.getString("item_name"));

        }
        return null;
    }


    public ArrayList<String> getAllIds() throws SQLException {

        ResultSet rst = CrudUtil.execute("select * from item");
        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()) {
            itemIds.add(rst.getString(1));
            itemIds.add(rst.getString(2));
        }

        return itemIds;
    }
    public  boolean update(ItemDto itemDto) throws SQLException {
        return CrudUtil.execute(
                "update item set item_name=? where item_id=?",
                itemDto.getItem_name(),
                itemDto.getItem_id()

        );
    }
    public  boolean delete(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where item_id=?", itemId);
    }


    public ArrayList<ItemDto> getAll() throws SQLException {
        // Execute SQL query to get all item IDs
            ArrayList<ItemDto> items = new ArrayList<>();

            // Database query to get all items
            ResultSet resultSet = CrudUtil.execute("SELECT item_id, item_name FROM item");

            while (resultSet.next()) {
                String itemId = resultSet.getString("item_id");
                String itemName = resultSet.getString("item_name");
                items.add(new ItemDto(itemId, itemName));
            }
            return items;
    }

}
