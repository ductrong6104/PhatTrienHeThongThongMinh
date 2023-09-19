package com.schoolproject.Drugstore.product.rate;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "ProductRate", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"productId", "customerId"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIdentityInfo is used when objects have parent child relationship.
// @JsonIdentityInfo is used to indicate that object identity will be used during serialization/de-serialization.
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rateId")
    private Integer id;

    @Column(name = "star")
    private Integer star;

    @Column(name = "time")
    private Date time;

    @ManyToOne
    @JoinColumn(name = "productId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Product product;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    public ProductRate(Integer star, Date time, Product product, Customer customer) {
        this.star = star;
        this.time = time;
        this.product = product;
        this.customer = customer;
    }
}
