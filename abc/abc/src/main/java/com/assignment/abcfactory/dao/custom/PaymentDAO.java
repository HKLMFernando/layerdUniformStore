package com.assignment.abcfactory.dao.custom;

import com.assignment.abcfactory.dao.CrudDAO;
import com.assignment.abcfactory.dto.PaymentDto;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<PaymentDto> {
    double getPaymont(String selectedPaymentId) throws SQLException;
    boolean isOrderAlreadyPaid(String orderId) throws SQLException;
}
