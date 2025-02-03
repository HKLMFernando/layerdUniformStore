package com.assignment.abcfactory.bo;

import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.dao.custom.impl.CustomerDAOImpl;
import com.assignment.abcfactory.model.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.dao.CrudUtil.execute;


public class CustomerBoImpl implements CustomerBO{

    CustomerDAOImpl customerDAO = new CustomerDAOImpl();
    @Override
    public String getNextId() throws SQLException {
        return customerDAO.getNextId();
    }

    @Override
    public CustomerDto search(String id) throws SQLException, ClassNotFoundException {
      return customerDAO.search(id);
    }
    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
       return customerDAO.save(customerDto);
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
       return customerDAO.getAll();
    }

    @Override
    public boolean update(CustomerDto customerDTO) throws SQLException {
       return customerDAO.update(customerDTO);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
      return customerDAO.exist(id);
    }

    @Override
    public boolean delete(String customerId) throws SQLException {
       return customerDAO.delete(customerId);
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException {
       return customerDAO.getAllCustomerIds();
    }

    @Override
    public CustomerDto findById(String selectedCusId) throws SQLException {
       return customerDAO.findById(selectedCusId);
    }
    @Override
    public CustomerDto findByCusId(String contact) throws SQLException {
       return customerDAO.findByCusId(contact);
    }
}
