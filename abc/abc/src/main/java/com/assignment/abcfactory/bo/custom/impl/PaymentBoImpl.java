package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.PaymentBO;
import com.assignment.abcfactory.dao.custom.impl.PaymentDAOImpl;
import com.assignment.abcfactory.model.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBO {
    PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
    @Override
    public  String getNextId() throws SQLException {
       return paymentDAO.getNextId();
    }

    @Override
    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
      return paymentDAO.search(id);
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {

        return paymentDAO.getAll();
    }

    @Override
    public boolean save(PaymentDto paymentDTO) throws SQLException {
       return paymentDAO.save(paymentDTO);
    }

    @Override
    public boolean update(PaymentDto dto) throws SQLException, ClassNotFoundException {
       return paymentDAO.update(dto);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
       return paymentDAO.exist(id);
    }
    @Override
    public double getPaymont(String selectedPaymentId) throws SQLException {
        return paymentDAO.getPaymont(selectedPaymentId);

    }
    @Override
    public boolean delete(String payment_id) throws SQLException {
        return paymentDAO.delete(payment_id);
    }
    @Override
    public boolean isOrderAlreadyPaid(String orderId) throws SQLException {
        return paymentDAO.isOrderAlreadyPaid(orderId);
    }
}
