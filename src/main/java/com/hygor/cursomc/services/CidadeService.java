package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Cidade;
import com.hygor.cursomc.repositories.CategoriaRepository;
import com.hygor.cursomc.repositories.CidadeRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public Cidade buscar(Integer id){
        Cidade obj = repository.findOne(id);
        if(obj == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Cidade.class.getName());
        }

        return obj;
    }

}
