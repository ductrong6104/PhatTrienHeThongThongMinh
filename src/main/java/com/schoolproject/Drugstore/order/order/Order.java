package com.schoolproject.Drugstore.order.order;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.order.status.OrderStatus;
import com.schoolproject.Drugstore.payment.Payment;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "[Order]")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
    private Integer id;

    @Column(name = "recipientName")
    private String recipientName;

    @Column(name = "recipientPhoneNumber")
    private String recipientPhoneNumber;

    @Column(name = "recipientAddress")
    private String recipientAddress;

    @Column(name = "buyOnline")
    private Boolean buyOnline;

    @ManyToOne
    @JoinColumn(name = "statusId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private OrderStatus orderStatus;

    @ManyToOne
    @JoinColumn(name = "paymentId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customerId")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

    @ManyToMany
    @JoinTable(name = "OrderDetail", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

    public Order(String recipientName, String recipientPhoneNumber, String recipientAddress, Boolean buyOnline, OrderStatus orderStatus, Payment payment, Customer customer, Collection<Product> products) {
        this.recipientName = recipientName;
        this.recipientPhoneNumber = recipientPhoneNumber;
        this.recipientAddress = recipientAddress;
        this.buyOnline = buyOnline;
        this.orderStatus = orderStatus;
        this.payment = payment;
        this.customer = customer;
        this.products = products;
    }
}
