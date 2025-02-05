package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.bo.SuperBo;
import com.assignment.abcfactory.model.ManuDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ManuBo  extends SuperBo {
    String getNextId() throws SQLException ;
    ManuDto search(String id) throws SQLException, ClassNotFoundException ;
    OrderDto findByOrderId(String selectedOrderId) throws SQLException;
    ArrayList<ManuDto> getAll() throws SQLException, ClassNotFoundException;
    boolean isOrderalredyAdded(String orderId) throws SQLException ;
    boolean save(ManuDto manuDto) throws SQLException, ClassNotFoundException;
    boolean delete(String manuId) throws SQLException, ClassNotFoundException;
    boolean update(ManuDto manuDto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
}
