package com.assignment.abcfactory.model;

import com.assignment.abcfactory.dto.ManuDto;
import com.assignment.abcfactory.dto.OrderDto;
import com.assignment.abcfactory.util.CrudUtil;
import com.assignment.abcfactory.dto.tm.ManuTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.util.CrudUtil.execute;

public class ManuModel {




    public static String getAllmanuID() throws SQLException {
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



    public static OrderDto findByOrderId(String selectedOrderId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from orders where order_id=?", selectedOrderId);

        if (rst.next()) {
            return new OrderDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getInt(4),  // Email
                    rst.getDouble(5),
                    rst.getString(6)// Phone
            );
        }
        return null;
    }




    public ArrayList<ManuDto> getallsmanudetails() throws SQLException {
        ResultSet rst = CrudUtil.execute("select manufacturing_id,prosses_details,order_id from manufacturing");
        ArrayList<ManuDto> manuDtos = new ArrayList<>();

        while (rst.next()) {
            ManuDto  manuDto = new ManuDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3)
            );

            manuDtos.add(manuDto);
        }
        return manuDtos;
    }

    public ArrayList<String> getAllordersID() throws SQLException {
        ResultSet rst = execute("select order_id from orders");

        ArrayList<String> orderId = new ArrayList<>();

        while (rst.next()) {
            orderId.add(rst.getString(1));
        }

        return orderId;
    }

    public boolean isOrderalredyAdded(String orderId) throws SQLException {
        String query = "SELECT COUNT(*) FROM manufacturing WHERE order_id = ?";
        ResultSet resultSet = CrudUtil.execute(query, orderId); // Assuming `CrudUtil.execute` executes prepared statements.
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0; // Returns true if the count is greater than 0
        }
        return false;
    }

    public boolean savemanuDetails(ManuDto manuDto) throws SQLException {
        return execute(
                "insert into manufacturing values (?,?,?)",
                manuDto.getManufacturing_id(),
                manuDto.getOrder_id(),
                manuDto.getProsses_details()

        );
    }

    public boolean deletemanudetails(String manuId) throws SQLException {
        return CrudUtil.execute("delete from manufacturing where manufacturing_id = ?", manuId);
    }

    public boolean updatemanuDetails(ManuDto manuDto) throws SQLException {
        return execute(
                "update manufacturing set prosses_details=?, order_id=? where manufacturing_id=?",
                manuDto.getProsses_details(),
                manuDto.getOrder_id(),
                manuDto.getManufacturing_id()
        );
    }
}
