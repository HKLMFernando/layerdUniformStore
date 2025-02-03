package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.OrderDAO;
import com.assignment.abcfactory.dto.CustomerDto;
import com.assignment.abcfactory.dto.OrderAndDetailDto;
import com.assignment.abcfactory.dto.OrderDetailsDto;
import com.assignment.abcfactory.dto.OrderDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.util.CrudUtil.execute;

public class OrderModel implements OrderDAO {


    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select order_id from orders order by order_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001"; // Return the default customer ID if no data is found
    }

    @Override
    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    public  boolean save(OrderDto orderDto) throws SQLException {
        return execute(
                "insert into orders values (?,?,?,?,?,?)",
                orderDto.getOrder_id(),
                orderDto.getOrder_date(),
                orderDto.getDue_date(),
                orderDto.getQty(),
                orderDto.getPrice_per_unit(),
                orderDto.getCust_id()
        );
    }


    public ArrayList<OrderAndDetailDto> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("select o.order_id, o.cust_id, od.item_id, o.order_date, o.due_date, o.qty, o.price_per_unit, od.total from orders o join order_details od where o.order_id = od.order_id");

        ArrayList<OrderAndDetailDto> orderAndDetailDtos = new ArrayList<>();

        while (rst.next()) {
            OrderAndDetailDto orderAndDetailDto = new OrderAndDetailDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),//adress
                    rst.getString(4),   // Phone
                    rst.getString(5),  // NIC
                    rst.getInt(6),// Email
                    rst.getDouble(7),// Email
                    rst.getDouble(8)// Email
            );
            orderAndDetailDtos.add(orderAndDetailDto);
        }
        return orderAndDetailDtos;
    }


    public boolean update(OrderDto orderDto) throws SQLException {
        return execute(
                "update orders set order_date=?, due_date=?, qty=?, price_per_unit=?,cust_id=? where order_id=?",
                orderDto.getOrder_date(),
                orderDto.getDue_date(),
                orderDto.getQty(),
                orderDto.getPrice_per_unit(),
                orderDto.getCust_id(),
                orderDto.getOrder_id()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public boolean delete(String orderId) throws SQLException {
        return execute("delete from orders where order_id=?", orderId);
    }


    public ArrayList<String> getAllOrderIds() throws SQLException {
        ResultSet rst = execute("select order_id from orders");

        ArrayList<String> orderIds = new ArrayList<>();

        while (rst.next()) {
            orderIds.add(rst.getString(1));
        }

        return orderIds;
    }



//    public static CustomerDto findById(String contact) throws SQLException {
//        ResultSet rst = CrudUtil.execute("SELECT * FROM customer WHERE contacts = ?", contact);
//
//        if (rst.next()) {
//            return new CustomerDto(
//                    rst.getString("cust_id"),    // Customer ID
//                    rst.getString("cust_name"),  // Name
//                    rst.getString("adress"),     // Address
//                    rst.getString("contacts"),    // Contact
//                    rst.getString("Nic"),        // NIC
//                    rst.getString("eMail")       // Email
//            );
//        }
//        return null;
//    }

}
