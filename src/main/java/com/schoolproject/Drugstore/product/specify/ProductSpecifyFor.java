package com.schoolproject.Drugstore.product.specify;

import java.util.Collection;

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
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductSpecifyFor")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSpecifyFor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specifyId")
    private int id;

    @Column(name = "specifyName")
    private String name;

    @ManyToMany
    @JoinTable(name = "ProductSpecifyDetail", joinColumns = @JoinColumn(name = "specifyId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}
