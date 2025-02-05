package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.bo.SuperBo;
import com.assignment.abcfactory.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBo {
    String getNextId() throws SQLException;
    CustomerDto search(String id) throws SQLException, ClassNotFoundException;
    boolean save(CustomerDto customerDto) throws SQLException;
    ArrayList<CustomerDto> getAll() throws SQLException;
    boolean update(CustomerDto customerDTO) throws SQLException;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    boolean delete(String customerId) throws SQLException ;
    ArrayList<String> getAllCustomerIds() throws SQLException ;
    CustomerDto findById(String selectedCusId) throws SQLException;
    CustomerDto findByCusId(String contact) throws SQLException ;
}
