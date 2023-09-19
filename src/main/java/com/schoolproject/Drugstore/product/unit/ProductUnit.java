package com.schoolproject.Drugstore.product.unit;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductUnit")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unitId")
    private Integer id;

    @Column(name = "unitName")
    private String name;

    @ManyToMany
    @JoinTable(name = "ProductUnitDetail", joinColumns = @JoinColumn(name = "productUnitId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

    public ProductUnit(String name, Collection<Product> products) {
        this.name = name;
        this.products = products;
    }
}
