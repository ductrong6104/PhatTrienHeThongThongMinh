package com.schoolproject.Drugstore.product.rate;

import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.product.product.Product;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ProductRateMapperDto {
    public ProductRateDto toDTO(ProductRate productRate){
        return new ProductRateDto(productRate.getId(), productRate.getStar(), productRate.getTime(),productRate.getCustomer().getFullName(), productRate.getProduct().getName() );
    }

    public ProductRate toProductRate(ProductRateCreationDto productRateCreationDto){
        return new ProductRate(productRateCreationDto.getStar(), productRateCreationDto.getTime(), new Product(), new Customer());
    }

}
