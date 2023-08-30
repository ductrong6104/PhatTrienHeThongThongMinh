package com.httm.WebBanThuocTay.model;

import java.util.Collection;

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
@Table(name = "NhomThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NhomThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNhom")
    private Integer id;

    @Column(name = "tenNhom" , unique = true)
    private String ten;

    //Một nhóm thuốc thuộc một loại thuốc
    @ManyToOne
    @JoinColumn(name = "idLoai")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private LoaiThuoc loaiThuoc;

    //Một nhóm thuốc có nhiều danh mục thuốc
    @OneToMany(mappedBy = "nhomThuoc", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DanhMucThuoc> danhMucThuoc; 

}
