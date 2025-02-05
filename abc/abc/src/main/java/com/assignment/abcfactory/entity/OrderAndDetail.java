package com.assignment.abcfactory.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderAndDetail {
    private String order_id;
    private String cust_id;
    private String item_id;
    private String order_date;
    private String due_date;
    private int qty;
    private double price_per_unit;
    private double total;
}
