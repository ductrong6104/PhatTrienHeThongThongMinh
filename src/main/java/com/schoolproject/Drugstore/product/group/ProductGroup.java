package com.schoolproject.Drugstore.product.group;

import java.util.Collection;

import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.type.ProductType;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductGroup")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "groupId")
    private int id;

    @Column(name = "groupName", unique = true)
    private String name;

    //Nhieu group co chung mot type
    @ManyToOne
    @JoinColumn(name = "typeId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductType productType;

    // Một group co nhieu category
    @OneToMany(mappedBy = "productGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductCategory> productCategories;

}
