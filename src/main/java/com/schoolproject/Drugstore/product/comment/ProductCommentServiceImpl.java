package com.schoolproject.Drugstore.product.comment;


import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.customer.CustomerRepository;
import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.product.ProductRepository;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@Service
public class ProductCommentServiceImpl implements ProductCommentService {
    private final ProductCommentRepository productCommentRepository;
    private final ProductCommentMapperDto productCommentMapperDto;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public ProductCommentServiceImpl(ProductCommentRepository productCommentRepository, ProductCommentMapperDto productCommentMapperDto, ProductCategoryRepository productCommentCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository productCommentDosageFormRepository, ProductSpecifyForRepository productCommentSpecifyForRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productCommentRepository = productCommentRepository;
        this.productCommentMapperDto = productCommentMapperDto;

        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public Collection<ProductCommentDto> getAllProductComments(){
        return productCommentRepository.findAll().stream().map(productComment -> productCommentMapperDto.toDTO(productComment)).toList();
    }

    @Override
    public ProductCommentDto getProductCommentById(Integer id){
        Optional<ProductComment> productComment =  productCommentRepository.findById(id);
        if (productComment.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productCommentMapperDto.toDTO(productCommentRepository.getReferenceById(id));
    }

    @Override
    public ProductCommentDto updateProductComment(ProductCommentCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductComment convertProduct = productCommentMapperDto.toProductComment(newProduct);
        ProductComment updateProduct = productCommentRepository.findById(id).map(productComment ->
                {

                    return productCommentRepository.save(productComment);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return productCommentRepository.save(convertProduct);
        });
        return productCommentMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductCommentDto addProductComment(ProductCommentCreationDto productCommentCreationDto){
        ProductComment productComment = productCommentMapperDto.toProductComment(productCommentCreationDto);
        productComment.setProduct(productRepository.getReferenceById(productCommentCreationDto.getProductId()));
        productComment.setCustomer(customerRepository.getReferenceById(productCommentCreationDto.getCustomerId()));
        if (productCommentCreationDto.getReplyForId() != null){
            productComment.setProductComment(productCommentRepository.getReferenceById(productCommentCreationDto.getReplyForId()));
        }

        productCommentRepository.save(productComment);
        return productCommentMapperDto.toDTO(productComment);
    }
    @Override
    public void deleteProductComment(Integer id){
        productCommentRepository.deleteById(id);
    }

    @Override
    public Collection<ProductCommentDto> filterCommentByProduct(Integer productId) {
        return productCommentRepository.filterCommentByProduct(productId).stream().map(productComment -> productCommentMapperDto.toDTO(productComment)).toList();
    }
}
