package com.assignment.abcfactory.model;


import com.assignment.abcfactory.dto.OrderDetailsDto;
import com.assignment.abcfactory.dto.OrderDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.SQLException;

public class OrderDetailsModel {
    public static boolean saveOrder(OrderDetailsDto orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "insert into order_details values (?,?,?)",
                orderDetailsDto.getItem_id(),
                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal()

        );

    }
    public boolean updateOrders(OrderDetailsDto orderDetailsDto) throws SQLException {
        return CrudUtil.execute(
                "update order_details set order_id=?, total=? where item_id=?",

                orderDetailsDto.getOrder_id(),
                orderDetailsDto.getTotal(),
                orderDetailsDto.getItem_id()

                );
    }

}
