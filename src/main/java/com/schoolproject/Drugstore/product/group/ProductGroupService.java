package com.schoolproject.Drugstore.product.group;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.type.ProductType;
import com.schoolproject.Drugstore.product.type.ProductTypeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductGroupService {
    private final ProductGroupRepository productGroupRepository;
    private final ProductGroupMapper productGroupMapper;
    private final ProductTypeRepository productTypeRepository;

    public Collection<ProductGroupDto> getAll() {
        List<ProductGroupDto> list = productGroupRepository.findAll().stream()
                .map(productGroupMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<ProductGroupDto> getByType(Integer typeId) {
        if (typeId == null)
            throw new DataNotFoundException();
        ProductType productType = productTypeRepository
                .findById(typeId)
                .orElseThrow(() -> new DataNotFoundException());
        List<ProductGroupDto> list = productGroupRepository.findByProductType(productType).stream()
                .map(productGroupMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductGroupDto getById(int id) {
        ProductGroup productGroup = productGroupRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
        return productGroupMapper.toDto(productGroup);
    }

    public ProductGroupDto create(ProductGroupDto productGroupDto) {
        try {
            productGroupDto.setId(0);
            ProductGroup entity = productGroupMapper.toEntity(productGroupDto);
            ProductGroup productGroup = productGroupRepository.save(entity);
            return productGroupMapper.toDto(productGroup);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public ProductGroupDto edit(ProductGroupDto productGroupDto) {
        Integer id = productGroupDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductGroup productGroup = productGroupRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        if (productGroupDto.getTypeId() == null) {
            productGroupDto.setTypeId(productGroup.getProductType().getId());
        }

        try {
            productGroup = productGroupMapper.toEntity(productGroupDto);
            productGroupRepository.save(productGroup);
            return productGroupMapper.toDto(productGroup);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductGroupDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductGroup productGroup = productGroupRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productGroupRepository.delete(productGroup);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return productGroupMapper.toDto(productGroup);
    }

    public Collection<ProductGroupDto> deleteAll() throws Exception {
        Collection<ProductGroupDto> list = getAll();

        try {
            productGroupRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<ProductGroupDto> getByName(String name) {
        List<ProductGroupDto> list = productGroupRepository.findByName(name).stream()
                .map(productGroupMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }
}
