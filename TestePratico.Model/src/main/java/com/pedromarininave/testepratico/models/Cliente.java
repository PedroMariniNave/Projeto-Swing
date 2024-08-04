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
@Table(name = "Clientes")
public final class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String codigo;
    private String nome;
    private BigDecimal valorLimiteCompra;
    private int diaFechamentoFatura;
}