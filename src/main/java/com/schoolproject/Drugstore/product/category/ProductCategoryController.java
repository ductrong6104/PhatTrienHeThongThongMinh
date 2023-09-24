package com.schoolproject.Drugstore.product.category;

import java.util.Collection;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.schoolproject.Drugstore.exception.customeException.RequestBodyEmptyException;

@RestController
@RequestMapping("/product/category")
@RequiredArgsConstructor
@CrossOrigin
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(name = "group", required = false) Integer groupId) {
        if (groupId == null)
            return ResponseEntity.ok().body(productCategoryService.getAll());
        else
            return ResponseEntity.ok().body(productCategoryService.getByGroup(groupId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productCategoryService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductCategoryDto productGroupDto) {
        // Request body empty
        if (productGroupDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductCategoryDto result = productCategoryService.create(productGroupDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductCategoryDto productGroupDto) {
        if (productGroupDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductCategoryDto result = productCategoryService.edit(productGroupDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        ProductCategoryDto result = productCategoryService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductCategoryDto> results = productCategoryService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }
}
