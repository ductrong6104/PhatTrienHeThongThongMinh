package com.schoolproject.Drugstore.product.product;

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
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(name = "category", required = false) Integer categoryId,
            @RequestParam(name = "brand", required = false) Integer brandId,
            @RequestParam(name = "dosageform", required = false) Integer dosageformId) {
        if (categoryId != null)
            return ResponseEntity.ok().body(productService.getByCategory(categoryId));
        else if (brandId != null)
            return ResponseEntity.ok().body(productService.getByBrand(brandId));
        else if (dosageformId != null)
            return ResponseEntity.ok().body(productService.getByDosageForm(dosageformId));
        else
            return ResponseEntity.ok().body(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(productService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) ProductDto productDto) {
        try {
            Thread.sleep(5000);

        } catch (Exception e) {
            // TODO: handle exception
        }
        // Request body empty
        if (productDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductDto result = productService.create(productDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) ProductDto productDto) {
        if (productDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductDto result = productService.edit(productDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        ProductDto result = productService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<ProductDto> results = productService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }

    @PutMapping("/details")
    public ResponseEntity<?> editDetails(@RequestParam(name = "product", required = true) Integer productId,
            @RequestBody(required = true) ProductDetailsDto productDetailsDto) throws InterruptedException {

        Thread.sleep(5000);

        if (productDetailsDto == null) {
            throw new RequestBodyEmptyException();
        }
        ProductDto result = productService.editDetails(productId, productDetailsDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }
}
