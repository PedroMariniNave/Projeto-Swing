package com.pedromarininave.testepratico.controllers;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Pedido;
import com.pedromarininave.testepratico.repositories.PedidosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(path = ApiConstants.PEDIDOS_PATH)
public class PedidosController {
    @Autowired
    private final PedidosRepository repository;

    public PedidosController(PedidosRepository repository) {
        this.repository = repository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable(name = "id") int id) {
        Optional<Pedido> pedido = repository.findById(id);

        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public Iterable<Pedido> findAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Pedido> save(@RequestBody Pedido pedido) {
        Pedido createdObject = repository.save(pedido);
        URI createdObjectUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdObject.getId()).toUri();

        return ResponseEntity.created(createdObjectUri).body(createdObject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> update(@PathVariable int id, @RequestBody Pedido pedido) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pedido.setId(id);
        Pedido updatedObject = repository.save(pedido);

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
    public ResponseEntity<Boolean> delete(@RequestBody Pedido pedido) {
        boolean isExistingEntity = repository.existsById(pedido.getId());
        if (!isExistingEntity) {
            return ResponseEntity.notFound().build();
        }

        repository.delete(pedido);
        return ResponseEntity.ok(true);
    }
}