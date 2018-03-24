package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Cliente;
import com.hygor.cursomc.domain.Estado;
import com.hygor.cursomc.repositories.ClienteRepository;
import com.hygor.cursomc.repositories.EstadoRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Integer id){
        Cliente cliente = repository.findOne(id);
        if(cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Cliente.class.getName());
        }

        return cliente;
    }

}
