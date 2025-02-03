package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
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
