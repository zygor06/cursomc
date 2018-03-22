package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Produto;
import com.hygor.cursomc.repositories.CategoriaRepository;
import com.hygor.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscar(Integer id){
        return repository.findOne(id);
    }

}
