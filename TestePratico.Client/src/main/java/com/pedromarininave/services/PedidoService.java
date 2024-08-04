package com.pedromarininave.services;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Pedido;
import com.pedromarininave.utils.JsonUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class PedidoService extends BaseService<Pedido> {

    public PedidoService() {
        super(ApiConstants.PEDIDOS_PATH);
    }

    public CompletableFuture<Pedido> findById(int id) {
        return getAsync(id).thenApply(response -> JsonUtils.deserialize(response, Pedido.class));
    }

    public CompletableFuture<List<Pedido>> findAll() {
        return getAsync().thenApply(response -> JsonUtils.deserializeList(response, Pedido.class));
    }

    public CompletableFuture<Pedido> save(Pedido pedido) {
        return postAsync(pedido).thenApply(response -> JsonUtils.deserialize(response, Pedido.class));
    }

    public CompletableFuture<Boolean> delete(int id) {
        return deleteAsync(id).thenApply(response -> JsonUtils.deserialize(response, Pedido.class));
    }
}