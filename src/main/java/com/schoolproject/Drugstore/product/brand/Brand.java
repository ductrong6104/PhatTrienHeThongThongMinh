package com.schoolproject.Drugstore.product.brand;

import java.util.Collection;

import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table(name = "Brand")
@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandId")
    private int id;

    @Column(name = "brandName", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "nationId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Nation nation;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}
