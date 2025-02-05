package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.CustomerBO;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.CustomerDAOImpl;
import com.assignment.abcfactory.entity.Customer;
import com.assignment.abcfactory.model.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerBoImpl implements CustomerBO {

    CustomerDAOImpl customerDAO =(CustomerDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);;
    @Override
    public String getNextId() throws SQLException {
        return customerDAO.getNextId();
    }

    @Override
    public CustomerDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CustomerDto customerDto) throws SQLException {
       return customerDAO.save(new Customer(customerDto.getCust_id(), customerDto.getCust_name(),customerDto.getAdress(),customerDto.getContacts(),customerDto.getNic(), customerDto.getEMail()));
    }

    @Override
    public ArrayList<CustomerDto> getAll() throws SQLException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers) {
            CustomerDto customerDto = new CustomerDto();
            customerDto.setCust_id(customer.getCust_id());
            customerDto.setCust_name(customer.getCust_name());
            customerDto.setAdress(customer.getAdress());
            customerDto.setContacts(customer.getContacts());
            customerDto.setNic(customer.getNic());
            customerDto.setEMail(customer.getEMail());
            customerDtos.add(customerDto);

        }
        return customerDtos;
    }

    @Override
    public boolean update(CustomerDto customerDto) throws SQLException {
       return customerDAO.update(new Customer(customerDto.getCust_id(), customerDto.getCust_name(),customerDto.getAdress(),customerDto.getContacts(),customerDto.getNic(), customerDto.getEMail()));
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
       return null;
    }
    @Override
    public CustomerDto findByCusId(String contact) throws SQLException {
       return null;
    }
}
