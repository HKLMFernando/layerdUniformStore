package com.assignment.abcfactory.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private String payment_id;
    private String payment_methord;
    private String date;
    private double payment;
    private String order_id;
}
