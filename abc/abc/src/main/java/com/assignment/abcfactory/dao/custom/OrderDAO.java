package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.entity.Order;
import com.assignment.abcfactory.model.OrderAndDetailDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO extends CrudDAO<Order> {
    ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException;
    ArrayList<String> getAllOrderIds() throws SQLException;
    OrderDto findByOrderId(String selectedOrderId) throws SQLException;
}
