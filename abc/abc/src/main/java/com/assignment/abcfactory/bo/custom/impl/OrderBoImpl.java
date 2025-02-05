package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.OrderBo;
import com.assignment.abcfactory.dao.custom.OrderDAO;
import com.assignment.abcfactory.dao.custom.impl.OrderDAOImpl;
import com.assignment.abcfactory.model.OrderAndDetailDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();

    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.search(id);
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }
    @Override
    public  boolean save(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(orderDto);
    }

    @Override
    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public boolean update(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(orderDto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.exist(id);
    }

    @Override
    public boolean delete(String orderId) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(orderId);
    }

    @Override
    public ArrayList<String> getAllOrderIds() throws SQLException {
        return orderDAO.getAllOrderIds();
    }
    @Override
    public OrderDto findByOrderId(String selectedOrderId) throws SQLException {
        return orderDAO.findByOrderId(selectedOrderId);
    }
}
