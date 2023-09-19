package com.schoolproject.Drugstore.order.order;

import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.order.status.OrderStatus;
import com.schoolproject.Drugstore.payment.Payment;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderMapperDto {
    public OrderDto toDTO(Order order){
        return new OrderDto(order.getId(), order.getRecipientName(), order.getRecipientPhoneNumber(), order.getRecipientAddress(), order.getBuyOnline(), order.getOrderStatus().getName(), order.getPayment().getName(), order.getCustomer().getFullName(),order.getProducts().stream().map(Product::getName).toList());
    }

    public Order toOrder(OrderCreationDto orderCreationDto){
        return new Order(orderCreationDto.getRecipientName(), orderCreationDto.getRecipientPhoneNumber(), orderCreationDto.getRecipientAddress(), orderCreationDto.getBuyOnLine(), new OrderStatus(),
        new Payment(), new Customer(), new ArrayList<>());
    }

}
