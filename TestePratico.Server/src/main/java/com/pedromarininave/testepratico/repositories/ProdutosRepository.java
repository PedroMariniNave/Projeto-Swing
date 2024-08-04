package com.pedromarininave.testepratico.repositories;

import com.pedromarininave.testepratico.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<Produto, Integer> {
}