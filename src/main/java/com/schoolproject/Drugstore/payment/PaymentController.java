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
public class PaymentController {
    private final PaymentService paymentService;
    private final PaymentModelAssembler paymentModelAssembler;


    @Autowired
    public PaymentController(PaymentService paymentService, PaymentModelAssembler paymentModelAssembler) {
        this.paymentService = paymentService;
        this.paymentModelAssembler = paymentModelAssembler;
    }

    @GetMapping("/payments/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        Payment payment = paymentService.getPaymentById(id);
        return paymentModelAssembler.toModel(payment);
    }
    @GetMapping("/payments")
    CollectionModel<EntityModel<Payment>> all(){
        List<EntityModel<Payment>> payments = paymentService.getAllPayments().stream().map(paymentModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(payments, linkTo(methodOn(PaymentController.class).all()).withSelfRel());
    }

    @PostMapping("/payments")
    ResponseEntity<?> newPayment(@RequestBody Payment payment){
        EntityModel<Payment> paymentEntityModel = paymentModelAssembler.toModel(paymentService.addPayment(payment));
        return ResponseEntity.created(paymentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(paymentEntityModel);
    }

    @PutMapping("payments/{id}")
    ResponseEntity<?> replacePayment(@RequestBody Payment payment, @PathVariable Integer id){
        Payment updatePayment = paymentService.updatePayment(payment, id);
        EntityModel<Payment> paymentEntityModel = paymentModelAssembler.toModel(updatePayment);
        return ResponseEntity.created(paymentEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(paymentEntityModel);
    }

    @DeleteMapping("payments/{id}")
    ResponseEntity<?> deletePayment(@PathVariable Integer id){
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}
