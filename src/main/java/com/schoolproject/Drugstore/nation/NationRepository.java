package com.schoolproject.Drugstore.nation;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Integer> {
    List<Nation> findByName(String name);

}
