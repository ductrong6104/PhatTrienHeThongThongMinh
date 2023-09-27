package com.schoolproject.Drugstore.product.specify;

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
@RequestMapping("/product/specify")
@RequiredArgsConstructor
@CrossOrigin
public class ProductSpecifyForController {

    private final ProductSpecifyForService productSpecifyForService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productSpecifyForService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productSpecifyForService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductSpecifyForDto productSpecifyForDto) {
        // Request body empty
        if (productSpecifyForDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductSpecifyForDto result = productSpecifyForService.create(productSpecifyForDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductSpecifyForDto productSpecifyForDto) {
        if (productSpecifyForDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductSpecifyForDto result = productSpecifyForService.edit(productSpecifyForDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        ProductSpecifyForDto result = productSpecifyForService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductSpecifyForDto> results = productSpecifyForService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }

    // orthers
    @GetMapping("/check")
    public ResponseEntity<?> checkSpecifyForName(@RequestParam(name = "field", required = true) String field,
            @RequestParam(name = "value", required = true) String value) {
        if (field.equals("name")) {
            Collection<ProductSpecifyForDto> results = productSpecifyForService.getByName(value);
            return ResponseEntity.ok().body(results != null ? results : new Object());
        }
        throw new RequestNotFoundException();
    }
}
