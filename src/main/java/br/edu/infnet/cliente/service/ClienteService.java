package br.edu.infnet.cliente.service;

import br.edu.infnet.cliente.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.cliente.exceptions.CpfInvalidoException;
import br.edu.infnet.cliente.model.domain.Cliente;
import br.edu.infnet.cliente.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClienteService {

    private static Logger log = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    ClienteRepository clienteRepository;

    public Collection<Cliente> obterLista() {
        return (Collection<Cliente>) clienteRepository.findAll();
    }

    public Cliente obterCliente(Integer clienteId) {
        return getOrElseThrow(clienteId);
    }

    public Cliente incluir(Cliente cliente) {
        if (cliente.getCpf() == null) {
            throw new CpfInvalidoException("Não é possível aceitar CPF nulo");
        }

        if (cliente.getCpf().isBlank()) {
            throw new CpfInvalidoException("Não é possível aceitar CPF vazio");
        }
        return clienteRepository.save(cliente);
    }

    public void excluir(Integer id) {
        getOrElseThrow(id);

        clienteRepository.deleteById(id);
    }

    private Cliente getOrElseThrow(Integer clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> {
                    String msg = String.format("cliente com id %s nao encontrado", clienteId);
                    log.error(msg);
                    return new ClienteNaoEncontradoException(msg);
                });
    }

}
