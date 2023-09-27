package com.schoolproject.Drugstore.product.unit;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.product.product.Product;

import com.schoolproject.Drugstore.product.unitDetail.ProductUnitDetail;
import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "productUnit", fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<ProductUnitDetail> productUnitDetails;

    public ProductUnit(String name, Collection<ProductUnitDetail> productUnitDetails) {
        this.name = name;
        this.productUnitDetails = productUnitDetails;
    }
}
