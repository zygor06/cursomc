package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.ItemPedido;
import com.hygor.cursomc.domain.PagamentoComBoleto;
import com.hygor.cursomc.domain.Pedido;
import com.hygor.cursomc.domain.enums.EstadoPagamento;
import com.hygor.cursomc.repositories.ItemPedidoRepository;
import com.hygor.cursomc.repositories.PagamentoRepository;
import com.hygor.cursomc.repositories.PedidoRepository;
import com.hygor.cursomc.repositories.ProdutoRepository;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public Pedido find(Integer id){
        Pedido pedido = pedidoRepository.findOne(id);
        if(pedido == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Pedido.class.getName());
        }

        return pedido;
    }

    public Pedido insert(Pedido pedido){

        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if(pedido.getPagamento() instanceof PagamentoComBoleto){
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        for(ItemPedido ip : pedido.getItens()){

            ip.setDesconto(0.0);
            ip.setPreco(produtoRepository.findOne(ip.getProduto().getId()).getPreco());
            ip.setPedido(pedido);

        }

        itemPedidoRepository.save(pedido.getItens());

        return pedido;
    }

}
