package com.schoolproject.Drugstore.product.brand;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.nation.NationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    private final BrandMapperDto brandMapperDto;
    private final NationRepository nationRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository, BrandMapperDto brandMapperDto, NationRepository nationRepository) {
        this.brandRepository = brandRepository;
        this.brandMapperDto = brandMapperDto;

        this.nationRepository = nationRepository;
    }

    public Collection<BrandDto> getAllProducts(){
        return brandRepository.findAll().stream().map(brand -> brandMapperDto.toDTO(brand)).toList();
    }

    public BrandDto getProductById(Integer id){
        Optional<Brand> brand =  brandRepository.findById(id);
        if (brand.isEmpty()){
            throw new DataNotFoundException(id, Brand.class.getSimpleName());

        }
        return brandMapperDto.toDTO(brandRepository.getReferenceById(id));
    }

    public BrandDto updateBrand(BrandCreationDto newBrand, Integer id){
        // parameter trong map se la object ma repository tim duoc
        Brand convertBrand = brandMapperDto.toBrand(newBrand);
        Brand updateBrand = brandRepository.findById(id).map(brand ->
                {
                // update brand existing
                    return brandRepository.save(brand);
                }).orElseGet(()->{
                    // create new brand
                convertBrand.setId(id);
                return brandRepository.save(convertBrand);
        });
        return brandMapperDto.toDTO(updateBrand);

    }
    public BrandDto addBrand(BrandCreationDto brandCreationDto){
        Brand brand = brandMapperDto.toBrand(brandCreationDto);
        Optional<Nation> nation = nationRepository.findById(brandCreationDto.getNationId());
        if (nation.isEmpty()){
            throw new DataNotFoundException(brandCreationDto.getNationId(), Brand.class.getSimpleName());
        }
        brand.setNation(nationRepository.getReferenceById(brandCreationDto.getNationId()));
        brandRepository.save(brand);
        return brandMapperDto.toDTO(brand);
    }

    public void deleteBrand(Integer id){
        brandRepository.deleteById(id);
    }


}
