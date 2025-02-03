package com.assignment.abcfactory.dao.custom.impl;


import com.assignment.abcfactory.dto.OrderDetailsDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.SQLException;

public class OrderDetailsDAOImpl {
    public  boolean save(OrderDetailsDto orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "insert into order_details values (?,?,?)",
                orderDetailsDto.getItem_id(),
                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal()

        );

    }
    public boolean update(OrderDetailsDto orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "update order_details set order_id=?, total=? where item_id=?",

                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal(),
                orderDetailsDto.getItem_id()

                );
    }

}
