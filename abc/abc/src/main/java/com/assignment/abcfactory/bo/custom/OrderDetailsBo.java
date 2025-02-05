package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.model.OrderDetailsDto;

import java.sql.SQLException;

public interface OrderDetailsBo {
    boolean save(OrderDetailsDto orderDetailsDto) throws SQLException;
    boolean update(OrderDetailsDto orderDetailsDto) throws SQLException ;
}
