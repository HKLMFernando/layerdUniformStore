package com.assignment.abcfactory.model;

import com.assignment.abcfactory.db.DBConnection;
import com.assignment.abcfactory.dto.OrderDto;
import com.assignment.abcfactory.dto.PaymentDto;
import com.assignment.abcfactory.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.assignment.abcfactory.util.CrudUtil.execute;

public class PaymentModel {

    public static String getNextPayId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select payment_id from payments order by payment_id desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("P%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "P001"; // Return the default customer ID if no data is found
    }


    public static ArrayList<PaymentDto> getAllPayments() throws SQLException {
        ResultSet rst = CrudUtil.execute("select payment_id, payment_methord,date,payment,order_id from payments");
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();

        while (rst.next()) {
            PaymentDto paymentDto = new PaymentDto(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),//adress
                    rst.getDouble(4),
                    rst.getString(5)
            );

            paymentDtos.add(paymentDto);
        }
        return paymentDtos;
    }
    public OrderDto findByOrderId(String selectedOrderId) throws SQLException {
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

    public ArrayList<String> getAllOrderIds() throws SQLException {
        ResultSet rst = execute("select order_id from orders");

        ArrayList<String> orderId = new ArrayList<>();

        while (rst.next()) {
            orderId.add(rst.getString(1));
        }

        return orderId;
    }

    public boolean savePayment(PaymentDto paymentDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into payments values (?,?,?,?,?)",
                paymentDTO.getPayment_id(),
                paymentDTO.getPayment_methord(),
                paymentDTO.getDate(),
                paymentDTO.getPayment(),
                paymentDTO.getOrder_id()

        );
    }
    public double getPaymont(String selectedPaymentId) throws SQLException {

        ResultSet rst = CrudUtil.execute("SELECT total FROM order_details WHERE order_id = ?", selectedPaymentId);
        if (rst.next()) {
            return rst.getDouble(1);
        }
        return 0;
    }
    public boolean deletePayment(String payment_id) throws SQLException {
        return CrudUtil.execute("delete from payments where payment_id = ?", payment_id);
    }

    public boolean isOrderAlreadyPaid(String orderId) throws SQLException {
        String query = "SELECT COUNT(*) FROM payments WHERE order_id = ?";
        ResultSet resultSet = CrudUtil.execute(query, orderId); // Assuming `CrudUtil.execute` executes prepared statements.
        if (resultSet.next()) {
            return resultSet.getInt(1) > 0; // Returns true if the count is greater than 0
        }
        return false;
    }


}
