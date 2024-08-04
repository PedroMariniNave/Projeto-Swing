package com.pedromarininave.testepratico.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@FieldNameConstants
@Table(name = "Produtos")
public final class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String codigo;
    private String descricao;
    private BigDecimal preco;
}