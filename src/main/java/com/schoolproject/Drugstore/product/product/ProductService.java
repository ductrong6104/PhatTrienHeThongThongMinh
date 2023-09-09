package com.schoolproject.Drugstore.product.product;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapperDto productMapperDto;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductMapperDto productMapperDto) {
        this.productRepository = productRepository;
        this.productMapperDto = productMapperDto;
    }

    public Collection<ProductDto> getAllProducts(){
        return productRepository.findAll().stream().map(product -> productMapperDto.toDTO(product)).toList();
    }

    public ProductDto getProductById(Integer id){
        Optional<Product> product =  productRepository.findById(id);
        if (product.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productMapperDto.toDTO(productRepository.getReferenceById(id));
    }

    public ProductDto updateProduct(ProductCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Product updateProduct = productRepository.findById(id).map(product ->
                {
                    product.setName(newProduct.getProductName());

                    return productRepository.save(product);
                }).orElseGet(()->{
                newProduct.setId(id);
                return productRepository.save(productMapperDto.toProduct(newProduct));
        });
        return productMapperDto.toDTO(updateProduct);

    }
    public ProductDto addProduct(Product product){
        productRepository.save(product);
        return productMapperDto.toDTO(product);
    }

    public void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }


}
