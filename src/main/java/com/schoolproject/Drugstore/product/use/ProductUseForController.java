package com.schoolproject.Drugstore.product.use;

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
@RequestMapping("/product/usefor")
@RequiredArgsConstructor
@CrossOrigin
public class ProductUseForController {

    private final ProductUseForService productUseForService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productUseForService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productUseForService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductUseForDto productUseForDto) {
        // Request body empty
        if (productUseForDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductUseForDto result = productUseForService.create(productUseForDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductUseForDto productUseForDto) {
        if (productUseForDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductUseForDto result = productUseForService.edit(productUseForDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "id") Integer id) {
        ProductUseForDto result = productUseForService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductUseForDto> results = productUseForService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }

    // orthers
    @GetMapping("/check")
    public ResponseEntity<?> checkUseForName(@RequestParam(name = "field", required = true) String field,
            @RequestParam(name = "value", required = true) String value) {
        if (field.equals("name")) {
            Collection<ProductUseForDto> results = productUseForService.getByName(value);
            return ResponseEntity.ok().body(results != null ? results : new Object());
        }
        throw new RequestNotFoundException();
    }
}
