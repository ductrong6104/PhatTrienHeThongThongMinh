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
@CrossOrigin
public class OrderController {
    private final OrderService orderService;
    private final OrderModelAssembler orderModelAssembler;
    private final OrderMapperDto orderMapperDto;


    @Autowired
    public OrderController(OrderService orderService, OrderModelAssembler orderModelAssembler, OrderMapperDto orderMapperDto) {
        this.orderService = orderService;
        this.orderModelAssembler = orderModelAssembler;
        this.orderMapperDto = orderMapperDto;
    }

    @GetMapping("/orders/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        OrderDto orderDto = orderService.getOrderById(id);
        return orderModelAssembler.toModel(orderDto);
    }
    @GetMapping("/orders")
    CollectionModel<EntityModel<OrderDto>> all(){
        List<EntityModel<OrderDto>> orders = orderService.getAllOrders().stream().map(orderModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @PostMapping("/orders")
    ResponseEntity<?> newOrder(@RequestBody OrderCreationDto orderCreationDto){
        EntityModel<OrderDto> orderEntityModel = orderModelAssembler.toModel(orderService.addOrder(orderCreationDto));
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @PutMapping("orders/{id}")
    ResponseEntity<?> replaceOrder(@RequestBody OrderCreationDto orderCreationDto, @PathVariable Integer id){
        OrderDto updateProduct = orderService.updateOrder(orderCreationDto, id);
        EntityModel<OrderDto> orderEntityModel = orderModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderEntityModel);
    }

    @DeleteMapping("orders/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
