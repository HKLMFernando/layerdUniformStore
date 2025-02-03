package com.assignment.abcfactory.view.tdm;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderTm {
    private String order_id;
    private String cust_id;
    private String item_id;
    private String order_date;
    private String due_date;
    private int qty;
    private double price_per_unit;
    private double total;
}
