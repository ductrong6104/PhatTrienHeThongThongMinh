package com.schoolproject.Drugstore.order.status;


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
public class OrderStatusServiceImpl implements OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;
    private final OrderStatusMapperDto orderStatusMapperDto;

    @Autowired
    public OrderStatusServiceImpl(OrderStatusRepository orderStatusRepository, OrderStatusMapperDto orderStatusMapperDto, ProductCategoryRepository orderStatusCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository orderStatusDosageFormRepository, ProductSpecifyForRepository orderStatusSpecifyForRepository) {
        this.orderStatusRepository = orderStatusRepository;
        this.orderStatusMapperDto = orderStatusMapperDto;

    }


    @Override
    public Collection<OrderStatusDto> getAllOrderStatuss(){
        return orderStatusRepository.findAll().stream().map(orderStatus -> orderStatusMapperDto.toDTO(orderStatus)).toList();
    }

    @Override
    public OrderStatusDto getOrderStatusById(Integer id){
        Optional<OrderStatus> orderStatus =  orderStatusRepository.findById(id);
        if (orderStatus.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return orderStatusMapperDto.toDTO(orderStatusRepository.getReferenceById(id));
    }

    @Override
    public OrderStatusDto updateOrderStatus(OrderStatusCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        OrderStatus convertProduct = orderStatusMapperDto.toOrderStatus(newProduct);
        OrderStatus updateProduct = orderStatusRepository.findById(id).map(orderStatus ->
                {

                    return orderStatusRepository.save(orderStatus);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return orderStatusRepository.save(convertProduct);
        });
        return orderStatusMapperDto.toDTO(updateProduct);

    }

    @Override
    public OrderStatusDto addOrderStatus(OrderStatusCreationDto orderStatusCreationDto){
        OrderStatus orderStatus = orderStatusMapperDto.toOrderStatus(orderStatusCreationDto);
        orderStatusRepository.save(orderStatus);
        return orderStatusMapperDto.toDTO(orderStatus);
    }
    @Override
    public void deleteOrderStatus(Integer id){
        orderStatusRepository.deleteById(id);
    }


}
