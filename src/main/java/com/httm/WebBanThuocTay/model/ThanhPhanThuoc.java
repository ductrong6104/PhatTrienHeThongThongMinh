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
@Table(name = "ThanhPhanThuoc")
//@Data: A shortcut for @ToString, @EqualsAndHashCode, @Getter on all fields, @Setter on all non-final fields, and @RequiredArgsConstructor!
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThanhPhanThuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThanhPhan")
    private Integer id;

    @Column(name = "tenThanhPhan", unique = true)
    private String ten;

    @Column(name = "mota")
    private String mota;

    
    // (n-n) Đối tượng sử dụng - thuốc
    @ManyToMany(mappedBy = "thanhPhanThuocs")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs;

}
