package com.assignment.abcfactory.bo.custom;

import com.assignment.abcfactory.bo.SuperBo;
import com.assignment.abcfactory.model.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PaymentBo extends SuperBo {
    String getNextId() throws SQLException ;
    PaymentDto search(String id) throws SQLException, ClassNotFoundException ;
    ArrayList<PaymentDto> getAll() throws SQLException ;
    boolean save(PaymentDto paymentDTO) throws SQLException ;
    boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException ;
    boolean exist(String id) throws SQLException, ClassNotFoundException ;
    double getPaymont(String selectedPaymentId) throws SQLException ;
    boolean delete(String payment_id) throws SQLException ;
    boolean isOrderAlreadyPaid(String orderId) throws SQLException;
}
