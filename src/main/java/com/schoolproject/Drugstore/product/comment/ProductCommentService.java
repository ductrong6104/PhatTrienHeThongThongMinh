package com.schoolproject.Drugstore.product.comment;


import com.schoolproject.Drugstore.exception.DataNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductCommentService {
    private final ProductCommentRepository productCommentRepository;
    @Autowired
    public ProductCommentService(ProductCommentRepository productCommentRepository) {
        this.productCommentRepository = productCommentRepository;
    }

    public Collection<ProductComment> getAllProductComments(){
        return productCommentRepository.findAll();
    }

    public ProductComment getProductCommentById(Integer id){
        Optional<ProductComment> productComment =  productCommentRepository.findById(id);
        if (productComment.isEmpty()){
            throw new DataNotFoundException(id, ProductComment.class.getSimpleName());

        }
        return productCommentRepository.getReferenceById(id);
    }

    public ProductComment updateProductComment(ProductComment newProductComment, Integer id){
        // parameter trong map se la object ma repository tim duoc

        ProductComment updateProductComment = productCommentRepository.findById(id).map(productComment ->
                {


                    return productCommentRepository.save(productComment);
                }).orElseGet(()->{
            newProductComment.setId(id);
            return productCommentRepository.save(newProductComment);
        });

        return updateProductComment;
    }
    public ProductComment addProductComment(ProductComment productComment){
        return productCommentRepository.save(productComment);
    }

    public void deleteProductComment(Integer id){
        productCommentRepository.deleteById(id);
    }


}
