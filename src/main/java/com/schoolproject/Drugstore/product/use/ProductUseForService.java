package com.schoolproject.Drugstore.product.use;

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
public class ProductUseForService {

    private final ProductUseForRepository productUseForRepository;
    private final ProductUseForMapper productUseForMapper;

    public Collection<ProductUseForDto> getAll() {
        List<ProductUseForDto> list = productUseForRepository.findAll().stream()
                .map(productUseForMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public ProductUseForDto getById(int id) {
        ProductUseFor productUseFor = productUseForRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        return productUseForMapper.toDto(productUseFor);
    }

    public ProductUseForDto create(ProductUseForDto productUseForDto) {
        productUseForDto.setId(0);
        try {
            ProductUseFor productUseFor = productUseForRepository
                    .save(productUseForMapper.toEntity(productUseForDto));
            return productUseForMapper.toDto(productUseFor);
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
    public ProductUseForDto edit(ProductUseForDto productUseForDto) {
        Integer id = productUseForDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductUseFor productUseFor = productUseForRepository
                .findById(productUseForDto.getId())
                .orElseThrow(() -> new DataNotFoundException());

        try {
            productUseFor = productUseForMapper.toEntity(productUseForDto);
            productUseForRepository.save(productUseFor);
            return productUseForMapper.toDto(productUseFor);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public ProductUseForDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        ProductUseFor productUseFor = productUseForRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            productUseForRepository.delete(productUseFor);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }

        return productUseForMapper.toDto(productUseFor);
    }

    public Collection<ProductUseForDto> deleteAll() throws Exception {
        Collection<ProductUseForDto> list = getAll();
        try {
            productUseForRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<ProductUseForDto> getByName(String name) {
        List<ProductUseForDto> list = productUseForRepository.findByName(name).stream()
                .map(productUseForMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

}
