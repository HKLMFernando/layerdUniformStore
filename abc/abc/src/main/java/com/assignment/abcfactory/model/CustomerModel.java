package com.assignment.abcfactory.model;


import com.assignment.abcfactory.util.CrudUtil;
import com.assignment.abcfactory.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.util.CrudUtil.execute;


public class CustomerModel {

    public String getNextCustomerId() throws SQLException {
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

    public boolean saveCustomer(CustomerDto customerDto) throws SQLException {
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


    public ArrayList<CustomerDto> getAllCustomers() throws SQLException {
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


    public boolean updateCustomer(CustomerDto customerDTO) throws SQLException {
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


    public boolean deleteCustomer(String customerId) throws SQLException {
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


    public static CustomerDto findById(String selectedCusId) throws SQLException {
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




}
