package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.entity.Payment;
import com.assignment.abcfactory.model.PaymentDto;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    double getPaymont(String selectedPaymentId) throws SQLException;
    boolean isOrderAlreadyPaid(String orderId) throws SQLException;
}
