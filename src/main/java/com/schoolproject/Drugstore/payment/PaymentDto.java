package com.schoolproject.Drugstore.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Integer id;
    private String name;
    private String description;
    private Collection<Integer> orders;
}
