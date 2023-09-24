package com.schoolproject.Drugstore.product.product;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.brand.Brand;
import com.schoolproject.Drugstore.brand.BrandRepository;
import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.category.ProductCategoryRepository;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageFormRepository;
import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final BrandRepository brandRepository;
    private final ProductDosageFormRepository productDosageFormRepository;

    public Collection<ProductDto> getAll() {
        List<ProductDto> list = productRepository.findAll().stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<ProductDto> getByCategory(Integer categoryId) {
        if (categoryId == null)
            throw new DataNotFoundException();
        ProductCategory productCategory = productCategoryRepository
                .findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException());

        List<ProductDto> list = productRepository.findByProductCategory(productCategory).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<ProductDto> getByBrand(Integer brandId) {
        if (brandId == null)
            throw new DataNotFoundException();
        Brand brand = brandRepository
                .findById(brandId)
                .orElseThrow(() -> new DataNotFoundException());

        List<ProductDto> list = productRepository.findByBrand(brand).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<ProductDto> getByDosageForm(Integer productDosageFormId) {
        if (productDosageFormId == null)
            throw new DataNotFoundException();
        ProductDosageForm productDosageForm = productDosageFormRepository
                .findById(productDosageFormId)
                .orElseThrow(() -> new DataNotFoundException());

        List<ProductDto> list = productRepository.findByProductDosageForm(productDosageForm).stream()
                .map(productMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductDto getById(int id) {
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productMapper.toDto(product);
    }

    public ProductDto create(ProductDto productDto) {
        try {
            productDto.setId(0);
            Product entity = productMapper.toEntity(productDto);
            Product product = productRepository.save(entity);
            System.out.println("\n\n\n\n\nCCCCCCC\n\n\n\n");
            return productMapper.toDto(product);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public ProductDto edit(ProductDto productDto) {
        Integer id = productDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        if (productDto.getCategoryId() == null) {
            productDto.setCategoryId(product.getProductCategory().getId());
        }
        if (productDto.getBrandId() == null) {
            productDto.setBrandId(product.getBrand().getId());
        }
        if (productDto.getDosageFormId() == null) {
            productDto.setDosageFormId(product.getProductDosageForm().getId());
        }

        try {
            product = productMapper.toEntity(productDto);
            productRepository.save(product);
            return productMapper.toDto(product);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productRepository.delete(product);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return productMapper.toDto(product);
    }

    public Collection<ProductDto> deleteAll() throws Exception {
        Collection<ProductDto> list = getAll();
        try {
            productRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }
}
