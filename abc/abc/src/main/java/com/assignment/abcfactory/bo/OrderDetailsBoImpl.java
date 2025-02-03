package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
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
