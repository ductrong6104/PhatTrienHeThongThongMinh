package com.schoolproject.Drugstore.product.type;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product/type")
@RequiredArgsConstructor
public class PoroductTypeController {

    private final ProductTypeService productTypeService;

    @GetMapping("/")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(productTypeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productTypeService.getById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ProductTypeDto productTypeDto) {
        ProductTypeDto result = productTypeService.create(productTypeDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable(name = "id") Integer id, @RequestBody ProductTypeDto productTypeDto) {
        ProductTypeDto result = productTypeService.create(productTypeDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

}
