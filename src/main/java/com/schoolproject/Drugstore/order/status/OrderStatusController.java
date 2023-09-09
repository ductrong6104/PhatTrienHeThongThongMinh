package com.schoolproject.Drugstore.order.status;


import com.schoolproject.Drugstore.order.order.Order;
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
public class OrderStatusController {
    private final OrderStatusService orderService;
    private final OrderStatusModelAssembler orderModelAssembler;


    @Autowired
    public OrderStatusController(OrderStatusService orderService, OrderStatusModelAssembler orderModelAssembler) {
        this.orderService = orderService;
        this.orderModelAssembler = orderModelAssembler;
    }

    @GetMapping("/orderStatus/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        OrderStatus orderStatus = orderService.getOrderStatusById(id);
        return orderModelAssembler.toModel(orderStatus);
    }
    @GetMapping("/orderStatus")
    CollectionModel<EntityModel<OrderStatus>> all(){
        List<EntityModel<OrderStatus>> orders = orderService.getAllOrderStatus().stream().map(orderModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(orders, linkTo(methodOn(OrderStatusController.class).all()).withSelfRel());
    }

    @PostMapping("/orderStatus")
    ResponseEntity<?> newOrder(@RequestBody OrderStatus order){
        EntityModel<OrderStatus> orderEntityModel = orderModelAssembler.toModel(orderService.addOrderStatus(order));
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @PutMapping("/orderStatus/{id}")
    ResponseEntity<?> replaceOrderStatus(@RequestBody OrderStatus orderStatus, @PathVariable Integer id){
        OrderStatus updateOrderStatus = orderService.updateOrderStatus(orderStatus, id);
        EntityModel<OrderStatus> orderEntityModel = orderModelAssembler.toModel(updateOrderStatus);
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @DeleteMapping("/orderStatus/{id}")
    ResponseEntity<?> deleteOrderStatus(@PathVariable Integer id){
        orderService.deleteOrderStatus(id);
        return ResponseEntity.noContent().build();
    }
}
