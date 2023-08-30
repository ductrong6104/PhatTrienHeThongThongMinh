package com.httm.WebBanThuocTay.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "DanhMucThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DanhMucThuoc {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDanhMuc")
    private Integer id;

    @Column(name = "tenDanhMuc" , unique = true)
    private String ten;

    //Một danh mục thuốc thuộc một nhóm thuốc
    @ManyToOne
    @JoinColumn(name = "idNhom")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private NhomThuoc nhomThuoc;

}
