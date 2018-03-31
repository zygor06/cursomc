package com.hygor.cursomc.repositories;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

    @Transactional(readOnly=true)
    Cliente findByEmail(String email);

}
