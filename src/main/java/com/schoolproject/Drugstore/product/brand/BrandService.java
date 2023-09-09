package com.schoolproject.Drugstore.product.brand;


import com.schoolproject.Drugstore.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class BrandService {
    private final BrandRepository brandRepository;
    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public Collection<Brand> getAllBrands(){
        List<Brand> brands = brandRepository.findAll();
        for (Brand b: brands){
            System.out.println(b);
        }
        return brands;
    }

    public Brand getBrandById(Integer id){
        Optional<Brand> brand =  brandRepository.findById(id);
        if (brand.isEmpty()){
            throw new DataNotFoundException(id, Brand.class.getSimpleName());

        }
        return brandRepository.getReferenceById(id);
    }

    public Brand updateBrand(Brand newBrand, Integer id){
        // parameter trong map se la object ma repository tim duoc

        Brand updateBrand = brandRepository.findById(id).map(brand ->
                {
                    brand.setNation(newBrand.getNation());
                    brand.setName(newBrand.getName());
                    brand.setDescription(newBrand.getDescription());
                    brand.setAvatar(newBrand.getAvatar());
                    return brandRepository.save(brand);
                }).orElseGet(()->{
            newBrand.setId(id);
            return brandRepository.save(newBrand);
        });

        return updateBrand;
    }
    public Brand addBrand(Brand brand){
        return brandRepository.save(brand);
    }

    public void deleteBrand(Integer id){
        brandRepository.deleteById(id);
    }


}
