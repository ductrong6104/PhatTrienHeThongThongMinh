package com.httm.WebBanThuocTay.entity.sub;

import java.sql.Date;

import com.httm.WebBanThuocTay.entity.main.Thuoc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "BinhLuanSanPham")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BinhLuanSanPham {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBinhLuan")
    private int id;

    @Column(name = "thoiGian")
    private Date thoiGian;

    @Column(name = "noiDung")
    private String noiDung;

    @Column(name = "idBinhLuanReply")
    private String idBinhLuanReply;

    // Một bình luận bởi một khách hàng
    @ManyToOne
    @JoinColumn(name = "idKhachHang")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private KhachHang khachHang;

    // Một bình luận bình luận một sản phẩm thuốc
    @ManyToOne
    @JoinColumn(name = "idThuoc")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Thuoc thuoc;

}
