package com.schoolproject.Drugstore.product.specify;

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
public class ProductSpecifyForService {

    private final ProductSpecifyForRepository productSpecifyForRepository;
    private final ProductSpecifyForMapper productSpecifyForMapper;

    public Collection<ProductSpecifyForDto> getAll() {
        List<ProductSpecifyForDto> list = productSpecifyForRepository.findAll().stream()
                .map(productSpecifyForMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductSpecifyForDto getById(int id) {
        ProductSpecifyFor productSpecifyFor = productSpecifyForRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productSpecifyForMapper.toDto(productSpecifyFor);
    }

    public ProductSpecifyForDto create(ProductSpecifyForDto productSpecifyForDto) {
        productSpecifyForDto.setId(0);
        try {
            ProductSpecifyFor productSpecifyFor = productSpecifyForRepository
                    .save(productSpecifyForMapper.toEntity(productSpecifyForDto));
            return productSpecifyForMapper.toDto(productSpecifyFor);
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
    public ProductSpecifyForDto edit(ProductSpecifyForDto productSpecifyForDto) {
        Integer id = productSpecifyForDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductSpecifyFor productSpecifyFor = productSpecifyForRepository
                .findById(productSpecifyForDto.getId())
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productSpecifyFor = productSpecifyForMapper.toEntity(productSpecifyForDto);
            productSpecifyForRepository.save(productSpecifyFor);
            return productSpecifyForMapper.toDto(productSpecifyFor);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductSpecifyForDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductSpecifyFor productSpecifyFor = productSpecifyForRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productSpecifyForRepository.delete(productSpecifyFor);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return productSpecifyForMapper.toDto(productSpecifyFor);
    }

    public Collection<ProductSpecifyForDto> deleteAll() throws Exception {
        Collection<ProductSpecifyForDto> list = getAll();
        try {
            productSpecifyForRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<ProductSpecifyForDto> getByName(String name) {
        List<ProductSpecifyForDto> list = productSpecifyForRepository.findByName(name).stream()
                .map(productSpecifyForMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

}
