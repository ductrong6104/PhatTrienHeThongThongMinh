package com.schoolproject.Drugstore.customer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping()
public class
CustomerController {
    private final CustomerService customerService;
    private final CustomerModelAssembler customerModelAssembler;
    private final CustomerMapperDto customerMapperDto;


    @Autowired
    public CustomerController(CustomerService customerService, CustomerModelAssembler customerModelAssembler, CustomerMapperDto customerMapperDto) {
        this.customerService = customerService;
        this.customerModelAssembler = customerModelAssembler;
        this.customerMapperDto = customerMapperDto;
    }

    @GetMapping("/customers/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        CustomerDto customerDto = customerService.getCustomerById(id);
        return customerModelAssembler.toModel(customerDto);
    }
    @GetMapping("/customers")
    CollectionModel<EntityModel<CustomerDto>> all(){
        List<EntityModel<CustomerDto>> customers = customerService.getAllCustomers().stream().map(customerModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @PostMapping("/customers")
    ResponseEntity<?> newCustomer(@RequestBody CustomerCreationDto customerCreationDto){
        EntityModel<CustomerDto> customerEntityModel = customerModelAssembler.toModel(customerService.addCustomer(customerCreationDto));
        return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
    }

    @PutMapping("customers/{id}")
    ResponseEntity<?> replaceCustomer(@RequestBody CustomerCreationDto customerCreationDto, @PathVariable Integer id){
        CustomerDto updateProduct = customerService.updateCustomer(customerCreationDto, id);
        EntityModel<CustomerDto> customerEntityModel = customerModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(customerEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(customerEntityModel);
    }

    @DeleteMapping("customers/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
