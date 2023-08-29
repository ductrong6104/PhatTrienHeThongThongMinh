package com.httm.WebBanThuocTay.entity.main;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Embeddable
public class HinhAnhThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHinhAnh")
    private int idHinhAnh;

    @Column(name = "hinhAnh", unique = true)
    private String hinhAnh;
    //Một hình ảnh cho một sản phẩm thuốc
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idThuoc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Thuoc thuoc;

}
