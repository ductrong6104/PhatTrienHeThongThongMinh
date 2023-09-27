package com.schoolproject.Drugstore.product.ingredient;

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
public class ProductIngredientService {

    private final ProductIngredientRepository productIngredientRepository;
    private final ProductIngredientMapper productIngredientMapper;

    public Collection<ProductIngredientDto> getAll() {
        List<ProductIngredientDto> list = productIngredientRepository.findAll().stream()
                .map(productIngredientMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductIngredientDto getById(int id) {
        ProductIngredient productIngredient = productIngredientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productIngredientMapper.toDto(productIngredient);
    }

    public ProductIngredientDto create(ProductIngredientDto productIngredientDto) {
        productIngredientDto.setId(0);
        try {
            ProductIngredient productIngredient = productIngredientRepository
                    .save(productIngredientMapper.toEntity(productIngredientDto));
            return productIngredientMapper.toDto(productIngredient);
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
    public ProductIngredientDto edit(ProductIngredientDto productIngredientDto) {
        Integer id = productIngredientDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductIngredient productIngredient = productIngredientRepository
                .findById(productIngredientDto.getId())
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productIngredient = productIngredientMapper.toEntity(productIngredientDto);
            productIngredientRepository.save(productIngredient);
            return productIngredientMapper.toDto(productIngredient);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductIngredientDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductIngredient productIngredient = productIngredientRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productIngredientRepository.delete(productIngredient);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return productIngredientMapper.toDto(productIngredient);
    }

    public Collection<ProductIngredientDto> deleteAll() throws Exception {
        Collection<ProductIngredientDto> list = getAll();
        try {
            productIngredientRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<ProductIngredientDto> getByName(String name) {
        List<ProductIngredientDto> list = productIngredientRepository.findByName(name).stream()
                .map(productIngredientMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

}
