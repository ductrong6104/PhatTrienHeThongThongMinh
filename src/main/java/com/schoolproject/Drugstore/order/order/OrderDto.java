package com.schoolproject.Drugstore.order.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;


// use for method get, return response kieu productDto cho client thay
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Integer id;
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private Boolean buyOnLine;
    private String orderStatusName;
    private String paymentName;
    private String customerName;
    private Collection<String> productNames;
}
