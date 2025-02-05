package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.entity.Manu;
import com.assignment.abcfactory.entity.Order;
import com.assignment.abcfactory.model.ManuDto;

import java.sql.SQLException;

public interface ManuDAO extends CrudDAO<Manu> {
    public Order findByOrderId(String selectedOrderId) throws SQLException;
    public boolean isOrderalredyAdded(String orderId) throws SQLException;
}
