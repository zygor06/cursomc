package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Produto;
import com.hygor.cursomc.repositories.CategoriaRepository;
import com.hygor.cursomc.repositories.ProdutoRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public Produto buscar(Integer id){
        Produto obj = repository.findOne(id);
        if(obj == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Produto.class.getName());
        }
        return obj;
    }

}
