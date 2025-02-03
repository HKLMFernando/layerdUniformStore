package com.assignment.abcfactory.view.tdm;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PaymentTm {
    private String payment_id;
    private String payment_methord;
    private String date;
    private double payment;
    private String order_id;



}
