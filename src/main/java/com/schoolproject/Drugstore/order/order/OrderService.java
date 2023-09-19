package com.schoolproject.Drugstore.order.order;

import java.util.Collection;

public interface OrderService {
    public Collection<OrderDto> getAllOrders();
    public OrderDto getOrderById(Integer id);
    public OrderDto updateOrder(OrderCreationDto orderCreationDto, Integer id);
    public OrderDto addOrder(OrderCreationDto orderCreationDto);
    public void deleteOrder(Integer id);
}
