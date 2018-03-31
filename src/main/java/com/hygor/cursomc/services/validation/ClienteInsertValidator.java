package com.hygor.cursomc.services.validation;

import com.hygor.cursomc.domain.Cliente;
import com.hygor.cursomc.domain.enums.TipoCliente;
import com.hygor.cursomc.dto.ClienteNewDTO;
import com.hygor.cursomc.repositories.ClienteRepository;
import com.hygor.cursomc.resources.exception.FieldMessage;
import com.hygor.cursomc.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert annotation) {
        //
    }

    @Override
    public boolean isValid(ClienteNewDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        //// <LOGICA>

        if(dto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(dto.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));


        if(dto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(dto.getCpfOuCnpj()))
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));


        Cliente aux = clienteRepository.findByEmail(dto.getEmail());

        if(aux != null) list.add(new FieldMessage("email", "Email já existente"));

        //// </LOGICA>

        for(FieldMessage e : list){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
            .addPropertyNode(e.getFieldName()).addConstraintViolation();
        }

        return list.isEmpty();
    }
}
