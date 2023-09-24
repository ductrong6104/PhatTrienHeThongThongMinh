package com.schoolproject.Drugstore.product.category;


import java.util.Collection;

import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.group.ProductGroup;
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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductCategory")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoryId")
    private int id;

    @Column(name = "categoryName", unique = true)
    private String name;

    //Nhieu group co chung mot type
    @ManyToOne
    @JoinColumn(name = "groupId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductGroup productGroup;

    //Một category co nhieu product
    @OneToMany(mappedBy = "productCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}
