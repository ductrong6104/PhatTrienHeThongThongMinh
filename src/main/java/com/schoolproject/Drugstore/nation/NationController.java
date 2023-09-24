package com.schoolproject.Drugstore.nation;

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
@RequestMapping("/nation")
@RequiredArgsConstructor
@CrossOrigin
public class NationController {

    private final NationService nationService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok().body(nationService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok().body(nationService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody(required = false) NationDto nationDto) {
        // Request body empty
        if (nationDto == null) {
            throw new RequestBodyEmptyException();
        }
        NationDto result = nationService.create(nationDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @PutMapping("")
    public ResponseEntity<?> edit(@RequestBody(required = false) NationDto nationDto) {
        if (nationDto == null) {
            throw new RequestBodyEmptyException();
        }
        NationDto result = nationService.edit(nationDto);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        NationDto result = nationService.delete(id);
        return ResponseEntity.ok().body(result != null ? result : new Object());
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete() throws Exception {
        Collection<NationDto> results = nationService.deleteAll();
        return ResponseEntity.ok().body(results != null ? results : new Object());
    }
}
