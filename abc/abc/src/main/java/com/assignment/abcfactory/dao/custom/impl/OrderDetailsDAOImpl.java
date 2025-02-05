package com.assignment.abcfactory.dao.custom.impl;


import com.assignment.abcfactory.dao.custom.OrderDetailDAO;
import com.assignment.abcfactory.entity.OrderDetails;
import com.assignment.abcfactory.model.OrderDetailsDto;
import com.assignment.abcfactory.dao.CrudUtil;

import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailDAO {
    public  boolean save(OrderDetails orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "insert into order_details values (?,?,?)",
                orderDetailsDto.getItem_id(),
                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal()

        );

    }
    public boolean update(OrderDetails orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "update order_details set order_id=?, total=? where item_id=?",

                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal(),
                orderDetailsDto.getItem_id()

                );
    }

}
