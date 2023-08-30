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
@Table(name = "DonViTinh")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonViTinh {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDonViTinh")
    private Integer id;

    @Column(name = "donViTinh", unique = true)
    private Integer donVi;

    // (n-n) Đối tượng sử dụng - thuốc
    @ManyToMany(mappedBy = "donViTinhs")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs;

}
