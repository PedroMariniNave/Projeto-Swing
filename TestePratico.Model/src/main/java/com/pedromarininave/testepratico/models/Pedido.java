package com.pedromarininave.testepratico.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Entity
@Getter
@Setter
@FieldNameConstants
@Table(name = "Pedidos")
public final class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Produto> produtosVendidos;
}