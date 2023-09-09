package com.schoolproject.Drugstore.product.comment;



import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductCommentModelAssembler implements RepresentationModelAssembler<ProductComment, EntityModel<ProductComment>> {
    @Override
    public EntityModel<ProductComment> toModel(ProductComment productComment){
        return EntityModel.of(productComment,
                linkTo(methodOn(ProductCommentController.class).one(productComment.getId())).withSelfRel(),
                linkTo(methodOn(ProductCommentController.class).all()).withRel("productComments"));
    }
}
