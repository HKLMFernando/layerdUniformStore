package com.assignment.abcfactory.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private String payment_id;
    private String payment_methord;
    private String date;
    private double payment;
    private String order_id;
}
