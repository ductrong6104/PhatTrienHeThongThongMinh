package com.schoolproject.Drugstore.customer;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping()
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;


    @Autowired
    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
    }

    @GetMapping("/customers/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        Customer customer = customerService.getCustomerById(id);
        return customerModelAssembler.toModel(customer);
    }
    @GetMapping("/customers")
    CollectionModel<EntityModel<Customer>> all(){
        List<EntityModel<Customer>> customers = customerService.getAllCustomers().stream().map(customerModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @PostMapping("/customers")
    ResponseEntity<?> newCustomer(@RequestBody Customer customer){
        EntityModel<Customer> customerEntityModel = customerModelAssembler.toModel(customerService.addCustomer(customer));
        return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
    }

    @PutMapping("customers/{id}")
    ResponseEntity<?> replaceCustomer(@RequestBody Customer customer, @PathVariable Integer id){
        Customer updateCustomer = customerService.updateCustomer(customer, id);
        EntityModel<Customer> customerEntityModel = customerModelAssembler.toModel(updateCustomer);
        return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
    }

    @DeleteMapping("customers/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
