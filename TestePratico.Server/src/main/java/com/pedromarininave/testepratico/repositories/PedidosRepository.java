package com.pedromarininave.testepratico.repositories;

import com.pedromarininave.testepratico.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidosRepository extends JpaRepository<Pedido, Integer> {
}