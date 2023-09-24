package com.schoolproject.Drugstore.product.product;

import java.util.Collection;

import com.schoolproject.Drugstore.order.order.Order;
import com.schoolproject.Drugstore.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.comment.ProductComment;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.rate.ProductRate;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import com.schoolproject.Drugstore.product.use.ProductUseFor;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "Product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private int id;

    @Column(name = "productName")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "uses")
    private String uses;

    @Column(name = "userManual")
    private String userManual;

    @Column(name = "sideEffects")
    private String sideEffects;

    @Column(name = "storage")
    private String storage;

    @Column(name = "note")
    private String note;

    @Column(name = "totalNumber")
    private int totalNumber;

    @Column(name = "soldNumber")
    private int soldNumber;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductCategory productCategory;

    @ManyToOne
    @JoinColumn(name = "brandId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "dosageFormId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDosageForm productDosageForm;

    @ManyToMany
    @JoinTable(name = "ProductSpecifyDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "specifyId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductSpecifyFor> productSpecifyFors;

    @ManyToMany
    @JoinTable(name = "ProductUseDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "useForId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductUseFor> productUseFors;

    @ManyToMany
    @JoinTable(name = "ProductUnitDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "productUnitId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductUnit> productUnits;

    @ManyToMany
    @JoinTable(name = "ProductIngredientDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "ingredientId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductIngredient> productIngredients;

    @ManyToMany
    @JoinTable(name = "OrderDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "orderId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Order> orders;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductImage> productImages;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductRate> productRates;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductComment> productComments;

}
