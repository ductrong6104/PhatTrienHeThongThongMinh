package com.schoolproject.Drugstore.product.type;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    public Collection<ProductTypeDto> getAll() {
        List<ProductTypeDto> list = productTypeRepository.findAll().stream()
                .map(productTypeMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductTypeDto getById(int id) {
        ProductType productType = productTypeRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
        return productTypeMapper.toDto(productType);
    }

    public ProductTypeDto create(ProductTypeDto productTypeDto) {
        productTypeDto.setId(0);
        try {
            ProductType productType = productTypeRepository.save(productTypeMapper.toEntity(productTypeDto));
            return productTypeMapper.toDto(productType);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    /*
     * Chỉ cho edit ko cho thêm
     * Phải find trong repository ra nếu không thì có thể bị nhầm thành thêm
     * Có thể đối tượng chuyển đổi (entity và dto) có nhiểu trường nên tốt nhất cho
     * mapper chuyển đổi cho nhanh
     */
    public ProductTypeDto edit(ProductTypeDto productTypeDto) {
        Integer id = productTypeDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductType productType = productTypeRepository
                .findById(productTypeDto.getId())
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productType = productTypeMapper.toEntity(productTypeDto);
            productTypeRepository.save(productType);
            return productTypeMapper.toDto(productType);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductTypeDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductType productType = productTypeRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productTypeRepository.delete(productType);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return productTypeMapper.toDto(productType);
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

    public Collection<ProductTypeDto> getByName(String name) {
        List<ProductTypeDto> list = productTypeRepository.findByName(name).stream()
                .map(productTypeMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

}
