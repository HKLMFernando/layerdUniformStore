package com.assignment.abcfactory.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String cust_id;
    private String cust_name;
    private String adress;
    private String contacts;
    private String Nic;
    private String eMail;
}
