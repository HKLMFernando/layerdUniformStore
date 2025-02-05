package com.assignment.abcfactory.bo.custom.impl;

import com.assignment.abcfactory.bo.custom.PaymentBo;
import com.assignment.abcfactory.dao.DAOFactory;
import com.assignment.abcfactory.dao.custom.impl.PaymentDAOImpl;
import com.assignment.abcfactory.entity.Payment;
import com.assignment.abcfactory.model.PaymentDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentBoImpl implements PaymentBo {
    PaymentDAOImpl paymentDAO = (PaymentDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PAYMENT);



    @Override
    public  String getNextId() throws SQLException {
       return paymentDAO.getNextId();
    }

    @Override
    public PaymentDto search(String id) throws SQLException, ClassNotFoundException {
      return null;
    }

    @Override
    public ArrayList<PaymentDto> getAll() throws SQLException {
        ArrayList<PaymentDto> paymentDtos = new ArrayList<>();
        ArrayList<Payment> payments = paymentDAO.getAll();
        for (Payment payment : payments) {
            PaymentDto paymentDto = new PaymentDto();
            paymentDto.setPayment_id(payment.getPayment_id());
            paymentDto.setPayment(payment.getPayment());
            paymentDto.setPayment_methord(payment.getPayment_methord());
            paymentDto.setDate(payment.getDate());
            paymentDto.setOrder_id(payment.getOrder_id());
            paymentDtos.add(paymentDto);

        }

        return paymentDtos;
    }

    @Override
    public boolean save(PaymentDto paymentDTO) throws SQLException {
       return paymentDAO.save(new Payment(paymentDTO.getPayment_id(),paymentDTO.getPayment_methord(),paymentDTO.getDate(),paymentDTO.getPayment(),paymentDTO.getOrder_id()));
    }

    @Override
    public boolean update(PaymentDto paymentDTO) throws SQLException, ClassNotFoundException {
       return paymentDAO.update(new Payment(paymentDTO.getPayment_id(),paymentDTO.getPayment_methord(),paymentDTO.getDate(),paymentDTO.getPayment(),paymentDTO.getOrder_id()));
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
