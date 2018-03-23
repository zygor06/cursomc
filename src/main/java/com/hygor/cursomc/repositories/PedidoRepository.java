package com.hygor.cursomc.repositories;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{}
