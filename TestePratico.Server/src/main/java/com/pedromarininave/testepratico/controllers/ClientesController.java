package com.pedromarininave.testepratico.controllers;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Cliente;
import com.pedromarininave.testepratico.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = ApiConstants.CLIENTES_PATH)
@Validated
public class ClientesController {
    @Autowired
    private final ClienteRepository repository;

    public ClientesController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable(name = "id") int id) {
        Optional<Cliente> cliente = repository.findById(id);

        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Iterable<Cliente> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody Cliente cliente) {
        Cliente createdObject = repository.save(cliente);
        URI createdObjectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObject.getId()).toUri();

        return ResponseEntity.created(createdObjectUri).body(createdObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable int id, @RequestBody Cliente cliente) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        Cliente updatedObject = repository.save(cliente);

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
    public ResponseEntity<Boolean> delete(@RequestBody Cliente cliente) {
        boolean isExistingEntity = repository.existsById(cliente.getId());
        if (!isExistingEntity) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(cliente);
        return ResponseEntity.ok(true);
    }
}