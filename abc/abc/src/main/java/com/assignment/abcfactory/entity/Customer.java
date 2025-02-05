package com.assignment.abcfactory.entity;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String cust_id;
    private String cust_name;
    private String adress;
    private String contacts;
    private String Nic;
    private String eMail;
}
