package com.schoolproject.Drugstore.order.order;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Collection<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id){
        Optional<Order> order =  orderRepository.findById(id);
        if (order.isEmpty()){
            throw new DataNotFoundException(id, Order.class.getSimpleName());

        }
        return orderRepository.getReferenceById(id);
    }

    public Order updateOrder(Order newOrder, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Order updateOrder = orderRepository.findById(id).map(order ->
                {
                    order.setOrderStatus(newOrder.getOrderStatus());

                    return orderRepository.save(order);
                }).orElseGet(()->{
            newOrder.setId(id);
            return orderRepository.save(newOrder);
        });

        return updateOrder;
    }
    public Order addOrder(Order order){
        return orderRepository.save(order);
    }

    public void deleteOrder(Integer id){
        orderRepository.deleteById(id);
    }


}
