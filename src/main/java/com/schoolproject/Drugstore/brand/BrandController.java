package com.schoolproject.Drugstore.brand;

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
@RequestMapping("/brand")
@RequiredArgsConstructor
@CrossOrigin
public class BrandController {

    private final BrandService brandService;

    @GetMapping("")
    public ResponseEntity<?> getAll(@RequestParam(name = "nation", required = false) Integer nationId) {
        if (nationId == null)
            return ResponseEntity.ok().body(brandService.getAll());
        else
            return ResponseEntity.ok().body(brandService.getByNation(nationId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(brandService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) BrandDto brandDto) {
        // Request body empty
        if (brandDto == null) {
            throw new RequestBodyEmptyException();
        }
        BrandDto result = brandService.create(brandDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) BrandDto brandDto) {
        if (brandDto == null) {
            throw new RequestBodyEmptyException();
        }
        BrandDto result = brandService.edit(brandDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        BrandDto result = brandService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<BrandDto> results = brandService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }
}
