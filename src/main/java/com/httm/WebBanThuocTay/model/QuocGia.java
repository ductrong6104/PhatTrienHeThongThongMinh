package com.httm.WebBanThuocTay.model;

import java.util.Collection;

import com.httm.WebBanThuocTay.model.ThuongHieu;

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
@Table(name = "QuocGia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuocGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuocGia")
    private Integer id;

    @Column(name = "tenQuocGia", unique = true)
    private String ten;

    // Một quốc gia có nhiều thương hiệu
    @OneToMany(mappedBy = "quocGia", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ThuongHieu> thuongHieu;

}
