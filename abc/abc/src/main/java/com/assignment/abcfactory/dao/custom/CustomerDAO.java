package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.model.CustomerDto;

import java.sql.SQLException;

public interface CustomerDAO extends CrudDAO<CustomerDto> {
     CustomerDto findByCusId(String contact) throws SQLException;
}
