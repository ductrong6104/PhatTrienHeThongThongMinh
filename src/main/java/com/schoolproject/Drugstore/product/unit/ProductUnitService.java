package com.schoolproject.Drugstore.product.unit;

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
public class ProductUnitService {

    private final ProductUnitRepository productUnitRepository;
    private final ProductUnitMapper productUnitMapper;

    public Collection<ProductUnitDto> getAll() {
        List<ProductUnitDto> list = productUnitRepository.findAll().stream()
                .map(productUnitMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductUnitDto getById(int id) {
        ProductUnit productUnit = productUnitRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productUnitMapper.toDto(productUnit);
    }

    public ProductUnitDto create(ProductUnitDto productUnitDto) {
        productUnitDto.setId(0);
        try {
            ProductUnit productUnit = productUnitRepository
                    .save(productUnitMapper.toEntity(productUnitDto));
            return productUnitMapper.toDto(productUnit);
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
    public ProductUnitDto edit(ProductUnitDto productUnitDto) {
        Integer id = productUnitDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductUnit productUnit = productUnitRepository
                .findById(productUnitDto.getId())
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productUnit = productUnitMapper.toEntity(productUnitDto);
            productUnitRepository.save(productUnit);
            return productUnitMapper.toDto(productUnit);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductUnitDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductUnit productUnit = productUnitRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productUnitRepository.delete(productUnit);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return productUnitMapper.toDto(productUnit);
    }

    public Collection<ProductUnitDto> deleteAll() throws Exception {
        Collection<ProductUnitDto> list = getAll();
        try {
            productUnitRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<ProductUnitDto> getByName(String name) {
        List<ProductUnitDto> list = productUnitRepository.findByName(name).stream()
                .map(productUnitMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

}
