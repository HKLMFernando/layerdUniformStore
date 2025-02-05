package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.OrderDetailsBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.OrderDetailsDAOImpl;
import com.assignment.abcfactory.entity.OrderDetails;
import com.assignment.abcfactory.model.OrderDetailsDto;

import java.sql.SQLException;

public class OrderDetailsBoImpl implements OrderDetailsBo {

    OrderDetailsDAOImpl dao = (OrderDetailsDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    @Override
    public  boolean save(OrderDetailsDto orderDetailsDto) throws SQLException {
        return dao.save(new OrderDetails(orderDetailsDto.getOrder_id(),orderDetailsDto.getItem_id(),orderDetailsDto.getTotal()));


    }
    @Override
    public boolean update(OrderDetailsDto orderDetailsDto) throws SQLException {
       return dao.update(new OrderDetails(orderDetailsDto.getOrder_id(),orderDetailsDto.getItem_id(),orderDetailsDto.getTotal()));
    }
}
