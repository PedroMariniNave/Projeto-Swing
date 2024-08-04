package com.pedromarininave.services;

import com.pedromarininave.testepratico.constants.ApiConstants;
import com.pedromarininave.testepratico.models.Produto;
import com.pedromarininave.utils.JsonUtils;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class ProdutoService extends BaseService<Produto> {

    public ProdutoService() {
        super(ApiConstants.PRODUTOS_PATH);
    }

    public CompletableFuture<Produto> findById(int id) {
        return getAsync(id).thenApply(response -> JsonUtils.deserialize(response, Produto.class));
    }

    public CompletableFuture<List<Produto>> findAll() {
        return getAsync().thenApply(response -> JsonUtils.deserializeList(response, Produto.class));
    }

    public CompletableFuture<Produto> save(Produto produto) {
        return postAsync(produto).thenApply(response -> JsonUtils.deserialize(response, Produto.class));
    }

    public CompletableFuture<Boolean> delete(int id) {
        return deleteAsync(id).thenApply(response -> JsonUtils.deserialize(response, Produto.class));
    }
}