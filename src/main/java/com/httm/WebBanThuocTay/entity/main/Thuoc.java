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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Thuoc")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Thuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThuoc")
    private int id;

    @Column(name = "tenThuoc", unique = true)
    private String ten;

    @Column(name = "mota")
    private String mota;

    @Column(name = "congDung")
    private String congDung;

    @Column(name = "cachDung")
    private String cachDung;

    @Column(name = "tacDungPhu")
    private String tacDungPhu;

    @Column(name = "baoQuan")
    private String baoQuan;

    @Column(name = "luuY")
    private String luuY;

    // Một sản phẩm thuốc thuộc một thương hiệu
    @ManyToOne
    @JoinColumn(name = "idThuongHieu")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ThuongHieu thuongHieu;

    // Một sản phẩm thuốc thuộc một danh mục
    @ManyToOne
    @JoinColumn(name = "idDanhMuc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DanhMucThuoc danhMucThuoc;

    // Một sản phẩm thuốc thuộc một dạng bào chế
    @ManyToOne
    @JoinColumn(name = "idDangBaoChe")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private DangBaoChe dangBaoChe;

    // (n-n) Thuốc - đối tượng sử dụng
    @ManyToMany
    @JoinTable(name = "DoiTuongDungThuoc", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDoiTuongSD"))
    protected Collection<DoiTuongSuDung> doiTuongSuDungs;

    // (n-n) Thuốc - đối tượng chỉ định
    @ManyToMany
    @JoinTable(name = "DoiTuongChiDinhThuoc", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDoiTuongCD"))
    protected Collection<DoiTuongChiDinh> doiTuongChiDinhs;

    // (n-n) Thuốc - thành phần
    @ManyToMany
    @JoinTable(name = "ChiTietThanhPhan", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idThanhPhan"))
    protected Collection<ThanhPhanThuoc> thanhPhanThuocs;

    // (n-n) Thuốc - thành phần
    @ManyToMany
    @JoinTable(name = "ChiTietDonViTinh", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDonViTinh"))
    protected Collection<DonViTinh> donViTinhs;

}
