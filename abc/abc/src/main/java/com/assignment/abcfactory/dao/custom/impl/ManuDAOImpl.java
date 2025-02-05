package com.assignment.abcfactory.dao.custom.impl;

import com.assignment.abcfactory.dao.custom.ManuDAO;
import com.assignment.abcfactory.entity.Manu;
import com.assignment.abcfactory.entity.Order;
import com.assignment.abcfactory.model.ManuDto;
import com.assignment.abcfactory.model.OrderDto;
import com.assignment.abcfactory.dao.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.dao.CrudUtil.execute;

public class ManuDAOImpl implements ManuDAO {




    public String getNextId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select manufacturing_id from manufacturing order by manufacturing_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring);// Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("M%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "M001"; // Return the default customer ID if no data is found
    }

    @Override
    public Manu search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }


    public Order findByOrderId(String selectedOrderId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from orders where order_id=?", selectedOrderId);

        if (rst.next()) {
            return new Order(
                    rst.getString(1),  // CustomerDto ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getInt(4),  // Email
                    rst.getDouble(5),
                    rst.getString(6)// Phone
            );
        }
        return null;
    }




    public ArrayList<Manu> getAll() throws SQLException {
        ResultSet rst = CrudUtil.execute("select manufacturing_id,prosses_details,order_id from manufacturing");
        ArrayList<Manu> manuDtos = new ArrayList<>();

        while (rst.next()) {
            Manu  manuDto = new Manu(
                    rst.getString(1),  // CustomerDto ID
                    rst.getString(2),  // Name
                    rst.getString(3)
            );

            manuDtos.add(manuDto);
        }
        return manuDtos;
    }

//    public ArrayList<String> getNextID() throws SQLException {
//        ResultSet rst = execute("select order_id from orders");
//
//        ArrayList<String> orderId = new ArrayList<>();
//
//        while (rst.next()) {
//            orderId.add(rst.getString(1));
//        }
//
//        return orderId;
//    }

    public boolean isOrderalredyAdded(String orderId) throws SQLException {
        String query = "SELECT COUNT(*) FROM manufacturing WHERE order_id = ?";
        ResultSet resultSet = CrudUtil.execute(query, orderId); // Assuming `CrudUtil.execute` executes prepared statements.
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0; // Returns true if the count is greater than 0
        }
        return false;
    }

    public boolean save(Manu manuDto) throws SQLException {
        return execute(
                "insert into manufacturing values (?,?,?)",
                manuDto.getManufacturing_id(),
                manuDto.getOrder_id(),
                manuDto.getProsses_details()

        );
    }

    public boolean delete(String manuId) throws SQLException {
        return CrudUtil.execute("delete from manufacturing where manufacturing_id = ?", manuId);
    }

    public boolean update(Manu manuDto) throws SQLException {
        return execute(
                "update manufacturing set prosses_details=?, order_id=? where manufacturing_id=?",
                manuDto.getProsses_details(),
                manuDto.getOrder_id(),
                manuDto.getManufacturing_id()
        );
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
