package com.assignment.abcfactory.dao.custom.impl;


import com.assignment.abcfactory.dao.custom.CustomerDAO;
import com.assignment.abcfactory.dao.CrudUtil;
import com.assignment.abcfactory.model.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.dao.CrudUtil.execute;


public class CustomerDAOImpl implements CustomerDAO {

    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select cust_id from customer order by cust_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }

    @Override
    public CustomerDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(CustomerDto customerDto) throws SQLException {
        return execute(
                "insert into customer values (?,?,?,?,?,?)",
                customerDto.getCust_id(),
                customerDto.getCust_name(),
                customerDto.getAdress(),
                customerDto.getContacts(),
                customerDto.getNic(),
                customerDto.getEMail()
                );
    }


    public ArrayList<CustomerDto> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),//adress
                    rst.getString(4),   // Phone
                    rst.getString(5),  // NIC
                    rst.getString(6)// Email
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }


    public boolean update(CustomerDto customerDTO) throws SQLException {
        return execute(
                "update customer set cust_name=?, adress=?, contacts=?, Nic=?, eMail=? where cust_id=?",
                customerDTO.getCust_name(),
                customerDTO.getAdress(),
                customerDTO.getContacts(),
                customerDTO.getNic(),
                customerDTO.getEMail(),
                customerDTO.getCust_id()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean delete(String customerId) throws SQLException {
        return execute("delete from customer where cust_id=?", customerId);
    }


    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = execute("select cust_id from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }


    public CustomerDto findById(String selectedCusId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where cust_id=?", selectedCusId);

        if (rst.next()) {
            return new CustomerDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getString(4),//adress
                    rst.getString(5),  // Email
                    rst.getString(6)   // Phone
            );
        }
        return null;
    }
    public CustomerDto findByCusId(String contact) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE contacts = ?", contact);

        if (rst.next()) {
            return new CustomerDto(
                    rst.getString("cust_id"),    // Customer ID
                    rst.getString("cust_name"),  // Name
                    rst.getString("adress"),     // Address
                    rst.getString("contacts"),    // Contact
                    rst.getString("Nic"),        // NIC
                    rst.getString("eMail")       // Email
            );
        }
        return null;
    }




}
