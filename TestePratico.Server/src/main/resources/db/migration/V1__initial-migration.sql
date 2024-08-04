CREATE TABLE Clientes (
   id SERIAL PRIMARY KEY,
   codigo VARCHAR(255),
   nome VARCHAR(255),
   valor_limite_compra DECIMAL(19, 2),
   dia_fechamento_fatura INT NOT NULL
);

CREATE TABLE Produtos (
   id SERIAL PRIMARY KEY,
   codigo VARCHAR(255),
   descricao VARCHAR(255),
   preco DECIMAL(19, 2)
);

CREATE TABLE Pedidos (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES Clientes(id)
        ON DELETE CASCADE
);

CREATE TABLE Pedidos_Produtos (
    pedido_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    PRIMARY KEY (pedido_id, produto_id),
    CONSTRAINT fk_pedido
        FOREIGN KEY(pedido_id)
        REFERENCES Pedidos(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_produto
        FOREIGN KEY(produto_id)
        REFERENCES Produtos(id)
        ON DELETE CASCADE
);
