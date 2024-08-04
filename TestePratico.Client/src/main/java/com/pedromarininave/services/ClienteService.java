package com.pedromarininave.services;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Cliente;
import com.pedromarininave.utils.JsonUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ClienteService extends BaseService<Cliente> {

    public ClienteService() {
        super(ApiConstants.CLIENTES_PATH);
    }

    public CompletableFuture<Cliente> findById(int id) {
        return getAsync(id).thenApply(response -> JsonUtils.deserialize(response, Cliente.class));
    }

    public CompletableFuture<List<Cliente>> findAll() {
        return getAsync().thenApply(response -> JsonUtils.deserializeList(response, Cliente.class));
    }

    public CompletableFuture<Cliente> save(Cliente cliente) {
        return postAsync(cliente).thenApply(response -> JsonUtils.deserialize(response, Cliente.class));
    }

    public CompletableFuture<Boolean> delete(int id) {
        return deleteAsync(id).thenApply(response -> JsonUtils.deserialize(response, Cliente.class));
    }
}