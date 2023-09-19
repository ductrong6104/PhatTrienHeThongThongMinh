package com.schoolproject.Drugstore.order.status;


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
OrderStatusController {
    private final OrderStatusService orderStatusService;
    private final OrderStatusModelAssembler orderStatusModelAssembler;
    private final OrderStatusMapperDto orderStatusMapperDto;


    @Autowired
    public OrderStatusController(OrderStatusService orderStatusService, OrderStatusModelAssembler orderStatusModelAssembler, OrderStatusMapperDto orderStatusMapperDto) {
        this.orderStatusService = orderStatusService;
        this.orderStatusModelAssembler = orderStatusModelAssembler;
        this.orderStatusMapperDto = orderStatusMapperDto;
    }

    @GetMapping("/orderStatuss/{id}")
    EntityModel<?> one(@PathVariable Integer id){
        OrderStatusDto orderStatusDto = orderStatusService.getOrderStatusById(id);
        return orderStatusModelAssembler.toModel(orderStatusDto);
    }
    @GetMapping("/orderStatuss")
    CollectionModel<EntityModel<OrderStatusDto>> all(){
        List<EntityModel<OrderStatusDto>> orderStatuss = orderStatusService.getAllOrderStatuss().stream().map(orderStatusModelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(orderStatuss, linkTo(methodOn(OrderStatusController.class).all()).withSelfRel());
    }

    @PostMapping("/orderStatuss")
    ResponseEntity<?> newOrderStatus(@RequestBody OrderStatusCreationDto orderStatusCreationDto){
        EntityModel<OrderStatusDto> orderStatusEntityModel = orderStatusModelAssembler.toModel(orderStatusService.addOrderStatus(orderStatusCreationDto));
        return ResponseEntity.created(orderStatusEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderStatusEntityModel);
    }

    @PutMapping("orderStatuss/{id}")
    ResponseEntity<?> replaceOrderStatus(@RequestBody OrderStatusCreationDto orderStatusCreationDto, @PathVariable Integer id){
        OrderStatusDto updateProduct = orderStatusService.updateOrderStatus(orderStatusCreationDto, id);
        EntityModel<OrderStatusDto> orderStatusEntityModel = orderStatusModelAssembler.toModel(updateProduct);
        return ResponseEntity.created(orderStatusEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(orderStatusEntityModel);
    }

    @DeleteMapping("orderStatuss/{id}")
    ResponseEntity<?> deleteProduct(@PathVariable Integer id){
        orderStatusService.deleteOrderStatus(id);
        return ResponseEntity.noContent().build();
    }
}
