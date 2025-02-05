package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.bo.SuperBo;
import com.assignment.abcfactory.model.OrderAndDetailDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBo  extends SuperBo {
    String getNextId() throws SQLException, ClassNotFoundException;
    OrderDto search(String id) throws SQLException, ClassNotFoundException ;
    ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException ;
    boolean save(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException ;
    boolean update(OrderDto orderDto) throws SQLException, ClassNotFoundException;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String orderId) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllOrderIds() throws SQLException ;
    OrderDto findByOrderId(String selectedOrderId) throws SQLException ;
}
