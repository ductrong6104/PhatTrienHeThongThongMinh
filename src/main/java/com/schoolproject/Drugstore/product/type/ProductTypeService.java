package com.schoolproject.Drugstore.product.type;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;

    public Collection<ProductTypeDto> getAll() {
        List<ProductTypeDto> list = productTypeRepository.findAll().stream()
                .map(type -> new ProductTypeDto(type.getId(), type.getName()))
                .collect(Collectors.toList());
        return list;
    }

    public ProductTypeDto getById(int id) {
        ProductType productType = productTypeRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
        return new ProductTypeDto(productType.getId(), productType.getName());
    }

    public ProductTypeDto create(ProductTypeDto productTypeDto) {

        try {
            new ProductType();
            ProductType productType = productTypeRepository
                    .save(ProductType.builder().id(0).name(productTypeDto.getName()).build());

            return new ProductTypeDto(productType.getId(), productType.getName());
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public ProductTypeDto edit(Integer id, ProductTypeDto productTypeDto) {
        ProductType productType = productTypeRepository.findById(id).map((type) -> {
            type.setName(productTypeDto.getName());
            return productTypeRepository.save(type);
        }).orElseThrow(() -> new DataNotFoundException());

        return ProductTypeDto.builder().id(productType.getId()).name(productType.getName()).build();
    }

    public ProductTypeDto delete(Integer id) {
        ProductType productType = productTypeRepository.findById(id).orElseThrow(() -> new DataNotFoundException());

        try {
            productTypeRepository.delete(productType);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return ProductTypeDto.builder().id(productType.getId()).name(productType.getName()).build();
    }

    public Collection<ProductTypeDto> deleteAll() throws Exception {
        Collection<ProductTypeDto> list = getAll();

        try {
            productTypeRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

}
