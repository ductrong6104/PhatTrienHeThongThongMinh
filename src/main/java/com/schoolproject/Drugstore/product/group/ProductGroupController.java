package com.schoolproject.Drugstore.product.group;

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
@RequestMapping("/product/group")
@RequiredArgsConstructor
@CrossOrigin
public class ProductGroupController {

    private final ProductGroupService productGroupService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(name = "type", required = false) Integer typeId) {
        if (typeId == null)
            return ResponseEntity.ok().body(productGroupService.getAll());
        else
            return ResponseEntity.ok().body(productGroupService.getByType(typeId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productGroupService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductGroupDto productGroupDto) {
        // Request body empty
        if (productGroupDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductGroupDto result = productGroupService.create(productGroupDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductGroupDto productGroupDto) {
        if (productGroupDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductGroupDto result = productGroupService.edit(productGroupDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        ProductGroupDto result = productGroupService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductGroupDto> results = productGroupService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }
}
