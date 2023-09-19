package com.schoolproject.Drugstore.customer;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.customer.CustomerRepository;
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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapperDto customerMapperDto;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapperDto customerMapperDto, ProductCategoryRepository customerCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository customerDosageFormRepository, ProductSpecifyForRepository customerSpecifyForRepository) {
        this.customerRepository = customerRepository;
        this.customerMapperDto = customerMapperDto;

    }


    @Override
    public Collection<CustomerDto> getAllCustomers(){
        return customerRepository.findAll().stream().map(customer -> customerMapperDto.toDTO(customer)).toList();
    }

    @Override
    public CustomerDto getCustomerById(Integer id){
        Optional<Customer> customer =  customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return customerMapperDto.toDTO(customerRepository.getReferenceById(id));
    }

    @Override
    public CustomerDto updateCustomer(CustomerCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Customer convertProduct = customerMapperDto.toCustomer(newProduct);
        Customer updateProduct = customerRepository.findById(id).map(customer ->
                {

                    return customerRepository.save(customer);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return customerRepository.save(convertProduct);
        });
        return customerMapperDto.toDTO(updateProduct);

    }

    @Override
    public CustomerDto addCustomer(CustomerCreationDto customerCreationDto){
        Customer customer = customerMapperDto.toCustomer(customerCreationDto);
        customerRepository.save(customer);
        return customerMapperDto.toDTO(customer);
    }
    @Override
    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }


}
