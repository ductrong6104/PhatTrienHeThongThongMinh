package com.schoolproject.Drugstore.product.dosageform;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.product.product.Product;

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
@Table(name = "ProductDosageForm")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductDosageForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dosageFormId")
    private Integer id;

    @Column(name = "dosageFormName")
    private String name;

    @OneToMany(mappedBy = "productDosageForm", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

    public ProductDosageForm(String name, Collection<Product> products) {
        this.name = name;
        this.products = products;
    }
}
