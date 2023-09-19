package com.schoolproject.Drugstore.product.brand;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.schoolproject.Drugstore.nation.Nation;
import com.schoolproject.Drugstore.product.product.Product;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Collection;

@Table(name = "Brand")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "brandId")
    private Integer id;

    @Column(name = "brandName", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "nationId")
    // JsonBackReference: phan sau cua reference, object Brand khong the tham chieu den object nation
    // muc dich them: de tranh get json infinite loop
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Nation nation;

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<Product> products;

    public Brand(String name, String description, String avatar, Nation nation, Collection<Product> products) {
        this.name = name;
        this.description = description;
        this.avatar = avatar;
        this.nation = nation;
        this.products = products;
    }
}
