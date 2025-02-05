package com.assignment.abcfactory.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private String item_id;
    private String order_id;
    private double total;
}
