package com.schoolproject.Drugstore.product.product;

import com.schoolproject.Drugstore.order.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Collection<Product> findByNameLike(String text);

    @Query("SELECT p from Product p JOIN p.productCategory pc JOIN pc.productGroup pg JOIN pg.productType pt WHERE pt.id = ?1")
    Collection<Product> filterProductsByType(Integer typeId);

    @Query("SELECT p from Product p JOIN p.productCategory pc JOIN pc.productGroup pg WHERE pg.id = ?1")
    Collection<Product> filterProductsByGroup(Integer groupId);

    @Query("SELECT p from Product p JOIN p.productCategory pc WHERE pc.id = ?1")
    Collection<Product> filterProductsByCategory(Integer categoryId);
    @Query("SELECT p from Product p JOIN p.brand b WHERE b.id = ?1")
    Collection<Product> filterProductsByBrand(Integer brandId);
    @Query("SELECT p from Product p JOIN p.productUseFors puf WHERE puf.id = ?1")
    Collection<Product> filterProductsByProductUseFor(Integer productUseForId);
    @Query("SELECT p from Product p JOIN p.productSpecifyFors psf WHERE psf.id = ?1")
    Collection<Product> filterProductsByProductSpecifyFor(Integer productSpecifyForId);

}
