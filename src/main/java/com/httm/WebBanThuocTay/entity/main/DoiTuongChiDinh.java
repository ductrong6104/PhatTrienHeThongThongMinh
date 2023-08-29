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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "DoiTuongChiDinh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoiTuongChiDinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDoiTuongChiDinh")
    private int id;

    @Column(name = "tenDoiTuongCD", unique = true)
    private String ten;

    // (n-n) Đối tượng chỉ định - thuốc
    @ManyToMany
    @JoinTable(name = "DoiTuongDungThuoc", joinColumns = @JoinColumn(name = "idDoiTuongSD"), inverseJoinColumns = @JoinColumn(name = "idThuoc"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs;

}