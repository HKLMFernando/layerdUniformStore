package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.entity.Customer;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<Customer> {
     Customer findByCusId(String contact) throws SQLException;
}
