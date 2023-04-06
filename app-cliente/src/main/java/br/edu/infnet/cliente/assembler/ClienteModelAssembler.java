package br.edu.infnet.cliente.assembler;

import br.edu.infnet.cliente.model.ClienteModel;
import br.edu.infnet.cliente.model.domain.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ClienteModelAssembler {

    @Autowired
    ModelMapper modelMapper;

    public ClienteModel toModel(Cliente cliente) {
        ClienteModel clienteModel = new ClienteModel();

        modelMapper.map(cliente, clienteModel);

        return clienteModel;
    }

    public List<ClienteModel> toCollection(Iterable<Cliente> entities) {

        return StreamSupport.stream(entities.spliterator(), false)
                .map(e -> toModel(e))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
