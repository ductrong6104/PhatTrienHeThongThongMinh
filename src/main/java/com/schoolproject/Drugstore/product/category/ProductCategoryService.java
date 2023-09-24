package com.schoolproject.Drugstore.product.category;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.product.group.ProductGroup;
import com.schoolproject.Drugstore.product.group.ProductGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final ProductGroupRepository productGroupRepository;

    public Collection<ProductCategoryDto> getAll() {
        List<ProductCategoryDto> list = productCategoryRepository.findAll().stream()
                .map(productCategoryMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<ProductCategoryDto> getByGroup(Integer groupId) {
        if (groupId == null)
            throw new DataNotFoundException();
        ProductGroup productGroup = productGroupRepository
                .findById(groupId)
                .orElseThrow(() -> new DataNotFoundException());

        List<ProductCategoryDto> list = productCategoryRepository.findByProductGroup(productGroup).stream()
                .map(productCategoryMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductCategoryDto getById(int id) {
        ProductCategory productCategory = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productCategoryMapper.toDto(productCategory);
    }

    public ProductCategoryDto create(ProductCategoryDto productCategoryDto) {
        try {
            productCategoryDto.setId(0);
            ProductCategory entity = productCategoryMapper.toEntity(productCategoryDto);
            ProductCategory productCategory = productCategoryRepository.save(entity);
            return productCategoryMapper.toDto(productCategory);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public ProductCategoryDto edit(ProductCategoryDto productCategoryDto) {
        Integer id = productCategoryDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductCategory productCategory = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        if (productCategoryDto.getGroupId() == null) {
            productCategoryDto.setGroupId(productCategory.getProductGroup().getId());
        }

        try {
            productCategory = productCategoryMapper.toEntity(productCategoryDto);
            productCategoryRepository.save(productCategory);
            return productCategoryMapper.toDto(productCategory);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductCategoryDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductCategory productCategory = productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productCategoryRepository.delete(productCategory);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return productCategoryMapper.toDto(productCategory);
    }

    public Collection<ProductCategoryDto> deleteAll() throws Exception {
        Collection<ProductCategoryDto> list = getAll();
        try {
            productCategoryRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }
}
