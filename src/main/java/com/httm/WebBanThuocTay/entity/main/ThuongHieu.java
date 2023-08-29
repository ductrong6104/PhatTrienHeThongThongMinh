package com.httm.WebBanThuocTay.entity.main;


import java.util.Collection;

import com.httm.WebBanThuocTay.entity.other.QuocGia;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ThuongHieu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThuongHieu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThuongHieu")
    private int id;

    @Column(name = "tenThuongHieu", unique = true)
    private String ten;

    @Column(name = "mota", unique = true)
    private String mota;

    @Column(name = "avataThuongHieu", unique = true)
    private String avata;

    // Một thương hiệu thuộc một quốc gia
    @ManyToOne
    @JoinColumn(name = "idQuocGia")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private QuocGia quocGia;

    // Một thương hiệu có nhiều sản phẩm thuốc
    @OneToMany(mappedBy = "thuongHieu", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> Thuoc;
}
