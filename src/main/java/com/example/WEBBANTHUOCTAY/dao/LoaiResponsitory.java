package com.example.WEBBANTHUOCTAY.dao;

import com.example.WEBBANTHUOCTAY.model.Loai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoaiResponsitory extends JpaRepository<Loai, Long> {
}
