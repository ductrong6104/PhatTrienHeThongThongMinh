package com.schoolproject.Drugstore.product.unitDetail;

import com.schoolproject.Drugstore.product.product.Product;
import com.schoolproject.Drugstore.product.unit.ProductUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Embeddable
@Data
public class ProductUnitDetailKey implements Serializable {
    @Column(name = "productUnitId")
    Integer productUnitId;

    @Column(name = "productId")
    Integer productId;
}
