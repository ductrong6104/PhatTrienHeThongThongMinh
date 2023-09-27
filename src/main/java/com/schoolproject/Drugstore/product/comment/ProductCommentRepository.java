package com.schoolproject.Drugstore.product.comment;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ProductCommentRepository extends JpaRepository<ProductComment, Integer> {
    @Query("SELECT pc FROM ProductComment pc JOIN pc.product p WHERE p.id = ?1")
    Collection<ProductComment> filterCommentByProduct(Integer productId);
}
