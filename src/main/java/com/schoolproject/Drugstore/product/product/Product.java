package com.schoolproject.Drugstore.product.product;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.order.order.Order;
import com.schoolproject.Drugstore.product.brand.Brand;
import com.schoolproject.Drugstore.product.category.ProductCategory;
import com.schoolproject.Drugstore.product.comment.ProductComment;
import com.schoolproject.Drugstore.product.dosageform.ProductDosageForm;
import com.schoolproject.Drugstore.product.image.ProductImage;
import com.schoolproject.Drugstore.product.ingredient.ProductIngredient;
import com.schoolproject.Drugstore.product.rate.ProductRate;
import com.schoolproject.Drugstore.product.specify.ProductSpecifyFor;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import com.schoolproject.Drugstore.product.unitDetail.ProductUnitDetail;
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
import lombok.*;

@Table(name = "Product")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Integer id;

    @Column(name = "productName")
    private String name;

    @Column(name = "description")
    private String description;

    // cach dung
    @Column(name = "uses")
    private String uses;

    // huong dan su dung
    @Column(name = "userManual")
    private String userManual;

    // tac dung phu
    @Column(name = "sideEffects")
    private String sideEffects;

    // bao quan
    @Column(name = "storage")
    private String storage;

    // luu y
    @Column(name = "note")
    private String note;

    // tong so luong
    @Column(name = "totalNumber")
    private int totalNumber;

    // so luong da ban
    @Column(name = "soldNumber")
    private int soldNumber;


    // loai san pham
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductCategory productCategory;

    // thuong hieu
    @ManyToOne
    @JoinColumn(name = "brandId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Brand brand;

    // dang bao che
    @ManyToOne
    @JoinColumn(name = "dosageFormId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductDosageForm productDosageForm;

    // doi tuong chi dinh
    @ManyToMany
    @JoinTable(name = "ProductSpecifyDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "specifyId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductSpecifyFor> productSpecifyFors;

    // doi tuong su dung
    @ManyToMany
    @JoinTable(name = "ProductUseDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "useForId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductUseFor> productUseFors;

    // don vi tinh
    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductUnitDetail> productUnitDetails;

    // thanh phan san pham
    @ManyToMany
    @JoinTable(name = "ProductIngredientDetail", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "ingredientId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductIngredient> productIngredients;

    // chi tiet dat hang
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

    // constructor to add new product


    public Product(String name, String description, String uses, String userManual, String sideEffects, String storage, String note, int totalNumber, int soldNumber, ProductCategory productCategory, Brand brand, ProductDosageForm productDosageForm, Collection<ProductSpecifyFor> productSpecifyFors, Collection<ProductUseFor> productUseFors, Collection<ProductUnitDetail> productUnitDetails, Collection<ProductIngredient> productIngredients,Collection<ProductImage> productImages) {
        this.name = name;
        this.description = description;
        this.uses = uses;
        this.userManual = userManual;
        this.sideEffects = sideEffects;
        this.storage = storage;
        this.note = note;
        this.totalNumber = totalNumber;
        this.soldNumber = soldNumber;
        this.productCategory = productCategory;
        this.brand = brand;
        this.productDosageForm = productDosageForm;
        this.productSpecifyFors = productSpecifyFors;
        this.productUseFors = productUseFors;
        this.productUnitDetails = productUnitDetails;
        this.productIngredients = productIngredients;
        this.productImages = productImages;
    }
}
