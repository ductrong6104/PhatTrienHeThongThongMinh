package com.httm.WebBanThuocTay.entity.main;


import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ThanhPhanThuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhPhanThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThanhPhan")
    private int id;

    @Column(name = "tenThanhPhan", unique = true)
    private String ten;

    @Column(name = "mota")
    private String mota;

    
    // (n-n) Đối tượng sử dụng - thuốc
    @ManyToMany
    @JoinTable(name = "DoiTuongDungThuoc", joinColumns = @JoinColumn(name = "idThanhPhan"), inverseJoinColumns = @JoinColumn(name = "idThuoc"))
    protected Collection<Thuoc> thuocs;

}
