package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.OrderBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.OrderDAO;
import com.assignment.abcfactory.dao.custom.impl.OrderDAOImpl;
import com.assignment.abcfactory.entity.Order;
import com.assignment.abcfactory.model.OrderAndDetailDto;
import com.assignment.abcfactory.model.OrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBoImpl implements OrderBo {
    OrderDAO orderDAO = (OrderDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDER);


    @Override
    public String getNextId() throws SQLException, ClassNotFoundException {
        return orderDAO.getNextId();

    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<OrderDto> orderDtos = new ArrayList<>();
        ArrayList<Order> orders = orderDAO.getAll();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setOrder_id(order.getOrder_id());
            orderDto.setOrder_date(order.getOrder_date());
            orderDto.setDue_date(order.getDue_date());
            orderDto.setQty(order.getQty());
            orderDto.setPrice_per_unit(order.getPrice_per_unit());
            orderDto.setCust_id(order.getCust_id());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
    @Override
    public  boolean save(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDAO.save(new Order(orderDto.getOrder_id(),orderDto.getOrder_date(),orderDto.getDue_date(),orderDto.getQty(),orderDto.getPrice_per_unit(),orderDto.getCust_id()));
    }

    @Override
    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public boolean update(OrderDto orderDto) throws SQLException, ClassNotFoundException {
        return orderDAO.update(new Order(orderDto.getOrder_id(),orderDto.getOrder_date(),orderDto.getDue_date(),orderDto.getQty(),orderDto.getPrice_per_unit(),orderDto.getCust_id()));
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
