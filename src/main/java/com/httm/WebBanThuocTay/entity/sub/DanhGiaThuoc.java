package com.httm.WebBanThuocTay.entity.sub;

import java.sql.Date;

import com.httm.WebBanThuocTay.entity.main.Thuoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "DanhGiaThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhGiaThuoc {

    @Column(name = "soSao")
    private int soSao;

    @Column(name = "thoiGian")
    private Date thoiGian;

    // Một đánh giá bởi một khách hàng
    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private KhachHang khachHang;

    // Một đánh giá đánh giá một sản phẩm thuốc
    @ManyToOne
    @JoinColumn(name = "idThuoc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Thuoc thuoc;

}
