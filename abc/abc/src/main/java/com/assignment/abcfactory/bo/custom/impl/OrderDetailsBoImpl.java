package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.OrderDetailsBo;
import com.assignment.abcfactory.dao.custom.impl.OrderDetailsDAOImpl;
import com.assignment.abcfactory.model.OrderDetailsDto;

import java.sql.SQLException;

public class OrderDetailsBoImpl implements OrderDetailsBo {

    OrderDetailsDAOImpl dao = new OrderDetailsDAOImpl();
    @Override
    public  boolean save(OrderDetailsDto orderDetailsDto) throws SQLException {
        return dao.save(orderDetailsDto);


    }
    @Override
    public boolean update(OrderDetailsDto orderDetailsDto) throws SQLException {
       return dao.update(orderDetailsDto);
    }
}
