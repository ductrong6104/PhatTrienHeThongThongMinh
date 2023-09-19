package com.schoolproject.Drugstore.payment;


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
PaymentController {
    private final PaymentService paymentService;
    private final PaymentModelAssembler paymentModelAssembler;
    private final PaymentMapperDto paymentMapperDto;


    @Autowired
    public PaymentController(PaymentService paymentService, PaymentModelAssembler paymentModelAssembler, PaymentMapperDto paymentMapperDto) {
        this.paymentService = paymentService;
        this.paymentModelAssembler = paymentModelAssembler;
        this.paymentMapperDto = paymentMapperDto;
    }

    @GetMapping("/payments/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        PaymentDto paymentDto = paymentService.getPaymentById(id);
        return paymentModelAssembler.toModel(paymentDto);
    }
    @GetMapping("/payments")
    CollectionModel<EntityModel<PaymentDto>> all(){
        List<EntityModel<PaymentDto>> payments = paymentService.getAllPayments().stream().map(paymentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(payments, linkTo(methodOn(PaymentController.class).all()).withSelfRel());
    }

    @PostMapping("/payments")
    ResponseEntity<?> newPayment(@RequestBody PaymentCreationDto paymentCreationDto){
        EntityModel<PaymentDto> paymentEntityModel = paymentModelAssembler.toModel(paymentService.addPayment(paymentCreationDto));
        return ResponseEntity.created(paymentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(paymentEntityModel);
    }

    @PutMapping("payments/{id}")
    ResponseEntity<?> replacePayment(@RequestBody PaymentCreationDto paymentCreationDto, @PathVariable Integer id){
        PaymentDto updateProduct = paymentService.updatePayment(paymentCreationDto, id);
        EntityModel<PaymentDto> paymentEntityModel = paymentModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(paymentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(paymentEntityModel);
    }

    @DeleteMapping("payments/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
