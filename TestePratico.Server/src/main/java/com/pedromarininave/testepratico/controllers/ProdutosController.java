package com.pedromarininave.testepratico.controllers;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Produto;
import com.pedromarininave.testepratico.repositories.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = ApiConstants.PRODUTOS_PATH)
public class ProdutosController {
    @Autowired
    private final ProdutosRepository repository;

    public ProdutosController(ProdutosRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable(name = "id") int id) {
        Optional<Produto> produto = repository.findById(id);

        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Iterable<Produto> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody Produto produto) {
        Produto createdObject = repository.save(produto);
        URI createdObjectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObject.getId()).toUri();

        return ResponseEntity.created(createdObjectUri).body(createdObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable int id, @RequestBody Produto produto) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        produto.setId(id);
        Produto updatedObject = repository.save(produto);

        return ResponseEntity.ok(updatedObject);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable(name = "id") int id) {
        boolean isExistingEntity = repository.existsById(id);
        if (!isExistingEntity) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping
    public ResponseEntity<Boolean> delete(@RequestBody Produto produto) {
        boolean isExistingEntity = repository.existsById(produto.getId());
        if (!isExistingEntity) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(produto);
        return ResponseEntity.ok(true);
    }
}