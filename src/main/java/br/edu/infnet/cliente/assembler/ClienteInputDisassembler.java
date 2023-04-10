package br.edu.infnet.cliente.assembler;

import br.edu.infnet.cliente.model.ClienteInput;
import br.edu.infnet.cliente.model.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClienteInputDisassembler {

    @Autowired
    ModelMapper modelMapper;

    public Cliente toDomainObject(ClienteInput ClienteInput) {
        return modelMapper.map(ClienteInput, Cliente.class);
    }

    public void copyToDomainObject(ClienteInput clienteInput, Cliente cliente) {
        modelMapper.map(clienteInput, cliente);
    }
}
