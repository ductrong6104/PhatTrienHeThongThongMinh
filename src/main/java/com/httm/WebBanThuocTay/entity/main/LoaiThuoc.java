package com.httm.WebBanThuocTay.entity.main;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "LoaiThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoaiThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLoai")
    private int id;

    @Column(name = "tenLoai", unique = true)
    private int ten;

    // Một loại thuốc có nhiều nhóm thuốc
    @OneToMany(mappedBy = "loaiThuoc", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<NhomThuoc> nhomThuoc;
}
