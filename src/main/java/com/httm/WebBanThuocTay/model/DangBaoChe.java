package com.httm.WebBanThuocTay.model;

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
@Table(name = "DangBaoChe")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DangBaoChe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDangBaoChe")
    private Integer id;

    @Column(name = "tenDangBaoChe" , unique = true)
    private String ten;

    //Một dạng bào chế có nhiều sản phẩm thuốc
    @OneToMany(mappedBy = "dangBaoChe", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs; 

}
