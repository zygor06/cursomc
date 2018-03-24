package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Pedido;
import com.hygor.cursomc.repositories.PedidoRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository repository;

    public Pedido find(Integer id){
        Pedido pedido = repository.findOne(id);
        if(pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Pedido.class.getName());
        }

        return pedido;
    }

}
