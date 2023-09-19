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

import java.util.Collection;
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
        Integer customerId = productCommentCreationDto.getCustomerId();
        Integer productId = productCommentCreationDto.getProductId();

        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);

        if (customer.isEmpty()){
            throw new DataNotFoundException(customerId, Customer.class.getSimpleName());
        }
        if (product.isEmpty()){
            throw new DataNotFoundException(productId, Product.class.getSimpleName());
        }

        productComment.setCustomer(customerRepository.getReferenceById(customerId));
        productComment.setProduct(productRepository.getReferenceById(productId));

        Integer replyForId = productCommentCreationDto.getReplyForId();
        assert replyForId != null : "replyForID null";
        if (replyForId != null){
            Optional<ProductComment> replyForComment = productCommentRepository.findById(replyForId);
            if (replyForComment.isEmpty()){
                throw new DataNotFoundException(replyForId, ProductComment.class.getSimpleName());
            }
            productComment.setProductComment(productCommentRepository.getReferenceById(replyForId));
        }

        productCommentRepository.save(productComment);
        return productCommentMapperDto.toDTO(productComment);
    }
    @Override
    public void deleteProductComment(Integer id){
        productCommentRepository.deleteById(id);
    }


}
