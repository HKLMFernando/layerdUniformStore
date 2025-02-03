package com.assignment.abcfactory.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDto {
    private String order_id;
    private String order_date;
    private String due_date;
    private int qty;
    private double price_per_unit;
    private String cust_id;


}
