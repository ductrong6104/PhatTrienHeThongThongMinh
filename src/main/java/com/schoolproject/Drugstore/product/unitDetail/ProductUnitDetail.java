package com.schoolproject.Drugstore.product.unitDetail;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "ProductUnitDetail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductUnitDetail {
    @EmbeddedId
    ProductUnitDetailKey id;

    @ManyToOne
    @MapsId("productUnitId")
    @JoinColumn(name = "productUnitId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductUnit productUnit;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "productId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @Column(name = "checkBaseUnit")
    private Boolean check;

    @Column(name = "convertValueUnit")
    private Integer convert;

    @Column(name = "price")
    private BigDecimal price;

}
