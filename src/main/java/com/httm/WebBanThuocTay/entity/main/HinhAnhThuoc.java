package com.httm.WebBanThuocTay.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "HinhAnhThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HinhAnhThuoc {

    @Column(name = "hinhAnh", unique = true)
    private String hinhAnh;

    //Một hình ảnh cho một sản phẩm thuốc
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idThuoc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Thuoc thuoc;

}
