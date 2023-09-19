package com.schoolproject.Drugstore.payment;

import com.schoolproject.Drugstore.order.order.Order;

import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PaymentMapperDto {
    public PaymentDto toDTO(Payment payment){
        return new PaymentDto(payment.getId(), payment.getName(), payment.getDescription(),payment.getOrders().stream().map(Order::getId).toList());
    }

    public Payment toPayment(PaymentCreationDto paymentCreationDto){
        return new Payment(paymentCreationDto.getName(), paymentCreationDto.getDescription(), new ArrayList<>());
    }

}
