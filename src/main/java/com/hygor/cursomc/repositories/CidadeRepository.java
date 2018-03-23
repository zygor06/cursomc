package com.hygor.cursomc.repositories;

import com.hygor.cursomc.domain.Cidade;
import com.hygor.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{}
