package com.schoolproject.Drugstore.product.ingredient;

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
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductIngredient")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductIngredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingredientId")
    private Integer id;

    @Column(name = "ingredientName")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "ProductIngredientDetail", joinColumns = @JoinColumn(name = "ingredientId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

}
