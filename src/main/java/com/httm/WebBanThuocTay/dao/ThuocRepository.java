package com.httm.WebBanThuocTay.dao;

import com.httm.WebBanThuocTay.model.Thuoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface ThuocRepository extends JpaRepository<Thuoc, Integer> {

}
