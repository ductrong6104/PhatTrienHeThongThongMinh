package com.schoolproject.Drugstore.order.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

// use for method post, get requestbody kieu productCreateDto
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderCreationDto {
    private String recipientName;
    private String recipientPhoneNumber;
    private String recipientAddress;
    private Boolean buyOnLine;
    private Integer orderStatus;
    private Integer paymentId;
    private Integer customerId;
    private Collection<Integer> productNames;
}
