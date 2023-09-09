package com.schoolproject.Drugstore.order.status;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    @Autowired
    public OrderStatusService(OrderStatusRepository orderStatusRepository) {
        this.orderStatusRepository = orderStatusRepository;
    }

    public Collection<OrderStatus> getAllOrderStatus(){
        return orderStatusRepository.findAll();
    }

    public OrderStatus getOrderStatusById(Integer id){
        Optional<OrderStatus> orderStatus =  orderStatusRepository.findById(id);
        if (orderStatus.isEmpty()){
            throw new DataNotFoundException(id, OrderStatus.class.getSimpleName());

        }
        return orderStatusRepository.getReferenceById(id);
    }

    public OrderStatus updateOrderStatus(OrderStatus newOrderStatus, Integer id){
        // parameter trong map se la object ma repository tim duoc

        OrderStatus updateOrderStatus = orderStatusRepository.findById(id).map(orderStatus ->
                {
                    orderStatus.setName(newOrderStatus.getName());
                    return orderStatusRepository.save(orderStatus);
                }).orElseGet(()->{
            newOrderStatus.setId(id);
            return orderStatusRepository.save(newOrderStatus);
        });

        return updateOrderStatus;
    }
    public OrderStatus addOrderStatus(OrderStatus orderStatus){
        return orderStatusRepository.save(orderStatus);
    }

    public void deleteOrderStatus(Integer id){
        orderStatusRepository.deleteById(id);
    }


}
