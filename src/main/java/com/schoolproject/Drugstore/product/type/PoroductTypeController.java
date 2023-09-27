package com.schoolproject.Drugstore.product.type;

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
@RequestMapping("/product/type")
@RequiredArgsConstructor
@CrossOrigin
public class PoroductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO: handle exception
        }

        return ResponseEntity.ok().body(productTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productTypeService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductTypeDto productTypeDto) throws Exception {
        // Request body empty
        Thread.sleep(1000);
        if (productTypeDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductTypeDto result = productTypeService.create(productTypeDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductTypeDto productTypeDto) {
        if (productTypeDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductTypeDto result = productTypeService.edit(productTypeDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam(name = "id", required = true) Integer id) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO: handle exception
        }
        ProductTypeDto result = productTypeService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/all")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductTypeDto> results = productTypeService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }

    // orthers
    @GetMapping("/check")
    public ResponseEntity<?> checkTypeName(@RequestParam(name = "field", required = true) String field,
            @RequestParam(name = "value", required = true) String value) {
        if (field.equals("name")) {
            Collection<ProductTypeDto> results = productTypeService.getByName(value);
            return ResponseEntity.ok().body(results != null ? results : new Object());
        }
        throw new RequestNotFoundException();
    }
}
