package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.model.ItemDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
    boolean save(ItemDto itemDto) throws SQLException;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    String getNextId() throws SQLException, ClassNotFoundException ;
    ItemDto search(String id) throws SQLException, ClassNotFoundException;
    ItemDto findById(String selectedItemId) throws SQLException ;
    ArrayList<String> getAllIds() throws SQLException ;
    boolean update(ItemDto itemDto) throws SQLException ;
    boolean delete(String itemId) throws SQLException ;
    ArrayList<ItemDto> getAll() throws SQLException ;
}
