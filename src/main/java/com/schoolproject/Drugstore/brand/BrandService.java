package com.schoolproject.Drugstore.brand;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.schoolproject.Drugstore.exception.customeException.CannotCreateDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotDeleteDataException;
import com.schoolproject.Drugstore.exception.customeException.CannotEditDataException;
import com.schoolproject.Drugstore.exception.customeException.DataNotFoundException;
import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.nation.NationRepository;
import com.schoolproject.Drugstore.product.type.ProductTypeDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;
    private final NationRepository nationRepository;

    public Collection<BrandDto> getAll() {
        List<BrandDto> list = brandRepository.findAll().stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public Collection<BrandDto> getByNation(Integer nationId) {
        if (nationId == null)
            throw new DataNotFoundException();
        Nation nation = nationRepository
                .findById(nationId)
                .orElseThrow(() -> new DataNotFoundException());

        List<BrandDto> list = brandRepository.findByNation(nation).stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    public BrandDto getById(int id) {
        Brand brand = brandRepository.findById(id).orElseThrow(() -> new DataNotFoundException());
        return brandMapper.toDto(brand);
    }

    public BrandDto create(BrandDto brandDto) {
        try {
            brandDto.setId(0);
            Brand entity = brandMapper.toEntity(brandDto);
            Brand brand = brandRepository.save(entity);
            return brandMapper.toDto(brand);
        } catch (Exception e) {
            throw new CannotCreateDataException();
        }
    }

    public BrandDto edit(BrandDto brandDto) {
        Integer id = brandDto.getId();
        if (id == null) {
            throw new DataNotFoundException();
        }
        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());

        if (brandDto.getNationId() == null) {
            brandDto.setNationId(brand.getNation().getId());
        }

        try {
            brand = brandMapper.toEntity(brandDto);
            brandRepository.save(brand);
            return brandMapper.toDto(brand);
        } catch (Exception e) {
            throw new CannotEditDataException();
        }
    }

    public BrandDto delete(Integer id) {
        if (id == null) {
            throw new DataNotFoundException();
        }
        Brand brand = brandRepository
                .findById(id)
                .orElseThrow(() -> new DataNotFoundException());
        try {
            brandRepository.delete(brand);
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return brandMapper.toDto(brand);
    }

    public Collection<BrandDto> deleteAll() throws Exception {
        Collection<BrandDto> list = getAll();

        try {
            brandRepository.deleteAll();
        } catch (Exception ex) {
            throw new CannotDeleteDataException();
        }
        return list;
    }

    public Collection<BrandDto> getByName(String name) {
        List<BrandDto> list = brandRepository.findByName(name).stream()
                .map(brandMapper::toDto)
                .collect(Collectors.toList());
        return list;
    }
}
