package com.schoolproject.Drugstore.brand;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.schoolproject.Drugstore.nation.Nation;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
    List<Brand> findByNation(Nation nation);

    List<Brand> findByName(String name);

}
