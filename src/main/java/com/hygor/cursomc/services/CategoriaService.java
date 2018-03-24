package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Categoria;
import com.hygor.cursomc.repositories.CategoriaRepository;
import com.hygor.cursomc.services.exceptions.DataIntegrityException;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class CategoriaService{

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Integer id){
        Categoria categoria = repository.findOne(id);
        if(categoria == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Categoria.class.getName());
        }

        return categoria;
    }

    public Categoria insert(Categoria categoria){
        categoria.setId(null);
        return repository.save(categoria);
    }

    public Categoria update(Categoria categoria){
        find(categoria.getId());
        return repository.save(categoria);
    }

    public void delete(Integer id){
        find(id);
        try{
            repository.delete(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos.");
        }

    }

}
