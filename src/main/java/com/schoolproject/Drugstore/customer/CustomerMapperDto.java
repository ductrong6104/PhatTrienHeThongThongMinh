package com.schoolproject.Drugstore.customer;

import com.schoolproject.Drugstore.order.order.Order;
import com.schoolproject.Drugstore.customer.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomerMapperDto {
    public CustomerDto toDTO(Customer customer){
        return new CustomerDto(customer.getId(), customer.getFirstName(), customer.getLastName(),customer.getEmail(),customer.getPhoneNumber(), customer.getPassword(),customer.getOrders().stream().map(Order::getId).toList());
    }

    public Customer toCustomer(CustomerCreationDto customerCreationDto){
        return new Customer(customerCreationDto.getFirstName(), customerCreationDto.getLastName(), customerCreationDto.getEmail(), customerCreationDto.getPhoneNumber(), customerCreationDto.getPassword());
    }

}
