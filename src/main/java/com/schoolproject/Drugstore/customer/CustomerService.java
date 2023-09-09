package com.schoolproject.Drugstore.customer;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Collection<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id){
        Optional<Customer> customer =  customerRepository.findById(id);
        if (customer.isEmpty()){
            throw new DataNotFoundException(id, Customer.class.getSimpleName());

        }
        return customerRepository.getReferenceById(id);
    }

    public Customer updateCustomer(Customer newCustomer, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Customer updateCustomer = customerRepository.findById(id).map(customer ->
                {
                    customer.setEmail(newCustomer.getEmail());
                    customer.setFirstName(newCustomer.getFirstName());
                    customer.setLastName(newCustomer.getLastName());
                    customer.setPhoneNumber(newCustomer.getPhoneNumber());
                    return customerRepository.save(customer);
                }).orElseGet(()->{
            newCustomer.setId(id);
            return customerRepository.save(newCustomer);
        });

        return updateCustomer;
    }
    public Customer addCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Integer id){
        customerRepository.deleteById(id);
    }


}
