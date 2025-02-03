package com.assignment.abcfactory.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsDto {
    private String item_id;
    private String order_id;
    private double total;
}
