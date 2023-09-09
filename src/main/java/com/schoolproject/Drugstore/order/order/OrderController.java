package com.schoolproject.Drugstore.order.order;


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
public class OrderController {
    private final OrderService orderService;
    private final OrderModelAssembler orderModelAssembler;


    @Autowired
    public OrderController(OrderService orderService, OrderModelAssembler orderModelAssembler) {
        this.orderService = orderService;
        this.orderModelAssembler = orderModelAssembler;
    }

    @GetMapping("/orders/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        Order order = orderService.getOrderById(id);
        return orderModelAssembler.toModel(order);
    }
    @GetMapping("/orders")
    CollectionModel<EntityModel<Order>> all(){
        List<EntityModel<Order>> orders = orderService.getAllOrders().stream().map(orderModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody Order order){
        EntityModel<Order> orderEntityModel = orderModelAssembler.toModel(orderService.addOrder(order));
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @PutMapping("orders/{id}")
    ResponseEntity<?> replaceOrder(@RequestBody Order order, @PathVariable Integer id){
        Order updateOrder = orderService.updateOrder(order, id);
        EntityModel<Order> orderEntityModel = orderModelAssembler.toModel(updateOrder);
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @DeleteMapping("orders/{id}")
    ResponseEntity<?> deleteOrder(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
