package com.schoolproject.Drugstore.product.dosageform;

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
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.schoolproject.Drugstore.exception.customeException.RequestBodyEmptyException;

@RestController
@RequestMapping("/product/dosageform")
@RequiredArgsConstructor
@CrossOrigin
public class ProductDosageFormController {

    private final ProductDosageFormService productDosageFormService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productDosageFormService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productDosageFormService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductDosageFormDto productDosageFormDto) {
        // Request body empty
        if (productDosageFormDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductDosageFormDto result = productDosageFormService.create(productDosageFormDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductDosageFormDto productDosageFormDto) {
        if (productDosageFormDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductDosageFormDto result = productDosageFormService.edit(productDosageFormDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        ProductDosageFormDto result = productDosageFormService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductDosageFormDto> results = productDosageFormService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }
}
