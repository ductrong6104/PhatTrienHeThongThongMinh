package com.schoolproject.Drugstore.order.order;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapperDto orderMapperDto;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderMapperDto orderMapperDto, ProductCategoryRepository orderCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository orderDosageFormRepository, ProductSpecifyForRepository orderSpecifyForRepository) {
        this.orderRepository = orderRepository;
        this.orderMapperDto = orderMapperDto;

    }


    @Override
    public Collection<OrderDto> getAllOrders(){
        return orderRepository.findAll().stream().map(order -> orderMapperDto.toDTO(order)).toList();
    }

    @Override
    public OrderDto getOrderById(Integer id){
        Optional<Order> order =  orderRepository.findById(id);
        if (order.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return orderMapperDto.toDTO(orderRepository.getReferenceById(id));
    }

    @Override
    public OrderDto updateOrder(OrderCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Order convertProduct = orderMapperDto.toOrder(newProduct);
        Order updateProduct = orderRepository.findById(id).map(order ->
                {

                    return orderRepository.save(order);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return orderRepository.save(convertProduct);
        });
        return orderMapperDto.toDTO(updateProduct);

    }

    @Override
    public OrderDto addOrder(OrderCreationDto orderCreationDto){
        Order order = orderMapperDto.toOrder(orderCreationDto);
        orderRepository.save(order);
        return orderMapperDto.toDTO(order);
    }
    @Override
    public void deleteOrder(Integer id){
        orderRepository.deleteById(id);
    }


}
