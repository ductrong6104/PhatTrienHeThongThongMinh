package com.schoolproject.Drugstore.product.ingredient;

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
import com.schoolproject.Drugstore.exception.customeException.RequestNotFoundException;

@RestController
@RequestMapping("/product/ingredient")
@RequiredArgsConstructor
@CrossOrigin
public class ProductIngredientController {

    private final ProductIngredientService productIngredientService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productIngredientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productIngredientService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductIngredientDto productIngredientDto) {
        // Request body empty
        if (productIngredientDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductIngredientDto result = productIngredientService.create(productIngredientDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductIngredientDto productIngredientDto) {
        if (productIngredientDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductIngredientDto result = productIngredientService.edit(productIngredientDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        ProductIngredientDto result = productIngredientService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductIngredientDto> results = productIngredientService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }

    // orthers
    @GetMapping("/check")
    public ResponseEntity<?> checkIngredientName(@RequestParam(name = "field", required = true) String field,
            @RequestParam(name = "value", required = true) String value) {
        if (field.equals("name")) {
            Collection<ProductIngredientDto> results = productIngredientService.getByName(value);
            return ResponseEntity.ok().body(results != null ? results : new Object());
        }
        throw new RequestNotFoundException();
    }
}
