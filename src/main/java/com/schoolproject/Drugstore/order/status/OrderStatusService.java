package com.schoolproject.Drugstore.order.status;

import java.util.Collection;

public interface OrderStatusService {
    public Collection<OrderStatusDto> getAllOrderStatuss();
    public OrderStatusDto getOrderStatusById(Integer id);
    public OrderStatusDto updateOrderStatus(OrderStatusCreationDto orderStatusCreationDto, Integer id);
    public OrderStatusDto addOrderStatus(OrderStatusCreationDto orderStatusCreationDto);
    public void deleteOrderStatus(Integer id);
}
