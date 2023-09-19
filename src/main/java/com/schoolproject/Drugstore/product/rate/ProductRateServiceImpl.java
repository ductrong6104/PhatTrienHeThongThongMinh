package com.schoolproject.Drugstore.product.rate;


import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.customer.CustomerRepository;
import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.brand.BrandRepository;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.product.ProductRepository;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyForRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductRateServiceImpl implements ProductRateService {
    private final ProductRateRepository productRateRepository;
    private final ProductRateMapperDto productRateMapperDto;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    @Autowired
    public ProductRateServiceImpl(ProductRateRepository productRateRepository, ProductRateMapperDto productRateMapperDto, ProductCategoryRepository productRateCategoryRepository, BrandRepository brandRepository, ProductDosageFormRepository productRateDosageFormRepository, ProductSpecifyForRepository productRateSpecifyForRepository, CustomerRepository customerRepository, ProductRepository productRepository) {
        this.productRateRepository = productRateRepository;
        this.productRateMapperDto = productRateMapperDto;

        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Collection<ProductRateDto> getAllProductRates(){
        return productRateRepository.findAll().stream().map(productRate -> productRateMapperDto.toDTO(productRate)).toList();
    }

    @Override
    public ProductRateDto getProductRateById(Integer id){
        Optional<ProductRate> productRate =  productRateRepository.findById(id);
        if (productRate.isEmpty()){
            throw new DataNotFoundException(id, Product.class.getSimpleName());

        }
        return productRateMapperDto.toDTO(productRateRepository.getReferenceById(id));
    }

    @Override
    public ProductRateDto updateProductRate(ProductRateCreationDto newProduct, Integer id){
        // parameter trong map se la object ma repository tim duoc
        ProductRate convertProduct = productRateMapperDto.toProductRate(newProduct);
        ProductRate updateProduct = productRateRepository.findById(id).map(productRate ->
                {

                    return productRateRepository.save(productRate);
                }).orElseGet(()->{
            convertProduct.setId(id);
                return productRateRepository.save(convertProduct);
        });
        return productRateMapperDto.toDTO(updateProduct);

    }

    @Override
    public ProductRateDto addProductRate(ProductRateCreationDto productRateCreationDto){
        ProductRate productRate = productRateMapperDto.toProductRate(productRateCreationDto);
        Integer customerId = productRateCreationDto.getCustomerId();
        Integer productId = productRateCreationDto.getProductId();
        Optional<Customer> customer = customerRepository.findById(customerId);
        Optional<Product> product = productRepository.findById(productId);
       
        if (customer.isEmpty()){
            throw new DataNotFoundException(customerId, Customer.class.getSimpleName());
        }
        if (product.isEmpty()){
            throw new DataNotFoundException(productId, Product.class.getSimpleName());
        }
        productRate.setCustomer(customerRepository.getReferenceById(customerId));
        productRate.setProduct(productRepository.getReferenceById(productId));

        productRateRepository.save(productRate);
        return productRateMapperDto.toDTO(productRate);
    }
    @Override
    public void deleteProductRate(Integer id){
        productRateRepository.deleteById(id);
    }


}
