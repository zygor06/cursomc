package com.hygor.cursomc.services;

import com.hygor.cursomc.domain.Cidade;
import com.hygor.cursomc.domain.Cliente;
import com.hygor.cursomc.domain.Endereco;
import com.hygor.cursomc.domain.enums.TipoCliente;
import com.hygor.cursomc.dto.ClienteDTO;
import com.hygor.cursomc.dto.ClienteNewDTO;
import com.hygor.cursomc.repositories.CidadeRepository;
import com.hygor.cursomc.repositories.ClienteRepository;
import com.hygor.cursomc.repositories.EnderecoRepository;
import com.hygor.cursomc.services.exceptions.DataIntegrityException;
import com.hygor.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    ///

    public Cliente find(Integer id){
        Cliente cliente = clienteRepository.findOne(id);
        if(cliente == null){
            throw new ObjectNotFoundException("Objeto não encontrado! ID:" + id + ", Tipo: " + Cliente.class.getName());
        }

        return cliente;
    }

    @Transactional
    public Cliente insert(Cliente cliente){
        cliente.setId(null);
        List<Endereco> enderecos = cliente.getEnderecos();
        for(Endereco e : enderecos){
            enderecoRepository.save(e);
        }
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    public Cliente update(Cliente cliente){
        Cliente newCliente = find(cliente.getId());
        updateData(newCliente, cliente);
        return clienteRepository.save(newCliente);
    }

    public void delete(Integer id){
        find(id);
        try{
            clienteRepository.delete(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir porque há pedidos relacionados");
        }

    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){

        PageRequest pageRequest = new PageRequest(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clienteRepository.findAll(pageRequest);

    }

    public Cliente fromDTO(ClienteDTO clienteDTO){
        return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
    }

    public Cliente fromDTO(ClienteNewDTO dto){
        Cliente cliente = new Cliente(
                null,
                dto.getNome(),
                dto.getEmail(),
                dto.getCpfOuCnpj(),
                TipoCliente.toEnum(dto.getTipo())
        );

        Cidade cidade = cidadeRepository.findOne(dto.getCidadeId());

        Endereco endereco = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cliente, cidade);

        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(dto.getTelefone1());

        //Checar telefones

        if (dto.getTelefone2() != null) cliente.getTelefones().add(dto.getTelefone2());
        if (dto.getTelefone3() != null) cliente.getTelefones().add(dto.getTelefone3());

        return cliente;
    }

    private void updateData(Cliente newCliente, Cliente cliente){
        newCliente.setNome(cliente.getNome());
        newCliente.setEmail(cliente.getEmail());
    }

}
