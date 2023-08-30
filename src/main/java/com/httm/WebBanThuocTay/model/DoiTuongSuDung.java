package com.httm.WebBanThuocTay.model;

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
@Table(name = "DoiTuongSuDung")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoiTuongSuDung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDoiTuongSuDung")
    private Integer id;

    @Column(name = "tenDoiTuongSD", unique = true)
    private String ten;

    // (n-n) Đối tượng sử dụng - thuốc
    // mappedBy: ten object n-n trong class Thuoc
    @ManyToMany(mappedBy = "doiTuongSuDungs")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs;

}
