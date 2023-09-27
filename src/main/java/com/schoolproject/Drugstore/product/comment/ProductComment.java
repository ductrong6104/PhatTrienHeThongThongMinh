package com.schoolproject.Drugstore.product.comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.customer.Customer;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Date;

@Entity
@Table(name = "ProductComment"  )
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class ProductComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentId")
    private Integer id;

    @Column(name = "subject")
    private String subject;
    @Column(name ="time")
    private Date date;
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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "replyForId", nullable = true)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private ProductComment productComment;

    public ProductComment(String subject, Date date, Product product, Customer customer) {
        this.subject = subject;
        this.date = date;
        this.product = product;
        this.customer = customer;
    }
}
