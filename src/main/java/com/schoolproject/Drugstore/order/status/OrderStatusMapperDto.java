package com.schoolproject.Drugstore.order.status;

import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class OrderStatusMapperDto {
    public OrderStatusDto toDTO(OrderStatus orderStatus){
        return new OrderStatusDto(orderStatus.getId(), orderStatus.getName(), orderStatus.getOrders().stream().map(Order::getId).toList());
    }

    public OrderStatus toOrderStatus(OrderStatusCreationDto orderStatusCreationDto){
        return new OrderStatus(orderStatusCreationDto.getName(), new ArrayList<>());
    }

}
