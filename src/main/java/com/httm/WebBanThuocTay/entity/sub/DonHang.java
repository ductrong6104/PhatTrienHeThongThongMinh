package com.httm.WebBanThuocTay.entity.sub;

import java.util.Collection;

import com.httm.WebBanThuocTay.entity.main.Thuoc;

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
@Table(name = "DonHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDonHang")
    private int id;

    @Column(name = "hoTenNguoiNhan")
    private String hoTenNguoiNhan;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "hinhThucMua")
    private boolean hinhThucMua;

    @Column(name = "trangThaiDonHang")
    private int trangThaiDonHang;

    // Một đơn hàng có một hình thức thanh toán
    @ManyToOne
    @JoinColumn(name = "idHinhThucThanhToan")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private HinhThucThanhToan hinhThucThanhToan;

    // Một đơn hàng của một khách hàng
    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private KhachHang khachHang;

    // (n-n) Đơn hàng - Thuốc
    @ManyToMany
    @JoinTable(name = "ChiTietDonHang", joinColumns = @JoinColumn(name = "idDonHang"), inverseJoinColumns = @JoinColumn(name = "idThuoc"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Thuoc> thuocs;

}
