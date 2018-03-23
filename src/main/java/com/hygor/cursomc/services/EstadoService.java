package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.domain.Estado;
import com.hygor.cursomc.repositories.CategoriaRepository;
import com.hygor.cursomc.repositories.EstadoRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository repository;

    public Estado buscar(Integer id){
        Estado obj = repository.findOne(id);
        if(obj == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Estado.class.getName());
        }

        return obj;
    }

}
