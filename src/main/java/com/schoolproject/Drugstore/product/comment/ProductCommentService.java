package com.schoolproject.Drugstore.product.comment;

import java.util.Collection;

public interface ProductCommentService {
    public Collection<ProductCommentDto> getAllProductComments();
    public ProductCommentDto getProductCommentById(Integer id);
    public ProductCommentDto updateProductComment(ProductCommentCreationDto productUnitCreationDto, Integer id);
    public ProductCommentDto addProductComment(ProductCommentCreationDto productUnitCreationDto);
    public void deleteProductComment(Integer id);
}
