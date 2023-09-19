package com.schoolproject.Drugstore.customer;

import java.util.Collection;

public interface CustomerService {
    public Collection<CustomerDto> getAllCustomers();
    public CustomerDto getCustomerById(Integer id);
    public CustomerDto updateCustomer(CustomerCreationDto customerCreationDto, Integer id);
    public CustomerDto addCustomer(CustomerCreationDto customerCreationDto);
    public void deleteCustomer(Integer id);
}
