package com.schoolproject.Drugstore.product.dosageform;

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
public class ProductDosageFormService {
    private final ProductDosageFormRepository productDosageFormRepository;
    private final ProductDosageFormMapper productDosageFormMapper;

    public Collection<ProductDosageFormDto> getAll() {
        List<ProductDosageFormDto> list = productDosageFormRepository.findAll().stream()
                .map(productDosageFormMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductDosageFormDto getById(int id) {
        ProductDosageForm productDosageForm = productDosageFormRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productDosageFormMapper.toDto(productDosageForm);
    }

    public ProductDosageFormDto create(ProductDosageFormDto productDosageFormDto) {
        try {
            productDosageFormDto.setId(0);
            ProductDosageForm entity = productDosageFormMapper.toEntity(productDosageFormDto);
            ProductDosageForm productDosageForm = productDosageFormRepository.save(entity);
            return productDosageFormMapper.toDto(productDosageForm);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public ProductDosageFormDto edit(ProductDosageFormDto productDosageFormDto) {
        Integer id = productDosageFormDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductDosageForm productDosageForm = productDosageFormRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productDosageForm = productDosageFormMapper.toEntity(productDosageFormDto);
            productDosageFormRepository.save(productDosageForm);
            return productDosageFormMapper.toDto(productDosageForm);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductDosageFormDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductDosageForm productDosageForm = productDosageFormRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productDosageFormRepository.delete(productDosageForm);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return productDosageFormMapper.toDto(productDosageForm);
    }

    public Collection<ProductDosageFormDto> deleteAll() throws Exception {
        Collection<ProductDosageFormDto> list = getAll();

        try {
            productDosageFormRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }
}
