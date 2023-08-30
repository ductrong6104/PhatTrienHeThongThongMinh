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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Thuoc")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Thuoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idThuoc")
    private Integer id;

    @NonNull
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

    public Thuoc(@NonNull String ten) {
        this.ten = ten;
    }

    // Một sản phẩm thuốc thuộc một thương hiệu
    //@NonNull
    @ManyToOne
    @JoinColumn(name = "idThuongHieu")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ThuongHieu thuongHieu;

    // Một sản phẩm thuốc thuộc một danh mục
    //@NonNull
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
    // @JoinTable voi :
    // name: la ten table sinh ra tu mqh n-n trong sql,
    // joinColumn: ten cot khoa ngoai trong table n-n,
    // inverseJoinColumns: ten cot khoa ngoai con lai trong table n-n
    @ManyToMany
    @JoinTable(name = "DoiTuongDungThuoc", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDoiTuongSD"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DoiTuongSuDung> doiTuongSuDungs;

    // (n-n) Thuốc - đối tượng chỉ định
    @ManyToMany
    @JoinTable(name = "DoiTuongChiDinhThuoc", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDoiTuongCD"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DoiTuongChiDinh> doiTuongChiDinhs;

    // (n-n) Thuốc - thành phần
    @ManyToMany
    @JoinTable(name = "ChiTietThanhPhan", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idThanhPhan"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ThanhPhanThuoc> thanhPhanThuocs;

    // (n-n) Thuốc - đơn vị tính
    @ManyToMany
    @JoinTable(name = "ChiTietDonViTinh", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDonViTinh"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DonViTinh> donViTinhs;

    // (n-n) Thuốc - đơn hàng
    @ManyToMany
    @JoinTable(name = "ChiTietDonHang", joinColumns = @JoinColumn(name = "idThuoc"), inverseJoinColumns = @JoinColumn(name = "idDonHang"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<DonHang> donHangs;

    // Một sản phẩm thuốc có nhiều hình ảnh
    @OneToMany(mappedBy = "thuoc", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<HinhAnhThuoc> hinhAnhThuocs;


}
