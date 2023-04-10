package br.edu.infnet.cliente.controller;

import br.edu.infnet.cliente.assembler.ClienteInputDisassembler;
import br.edu.infnet.cliente.assembler.ClienteModelAssembler;
import br.edu.infnet.cliente.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.cliente.model.ClienteInput;
import br.edu.infnet.cliente.model.ClienteModel;
import br.edu.infnet.cliente.model.domain.Cliente;
import br.edu.infnet.cliente.service.ClienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    ClienteService clienteService;

    @Autowired
    ClienteModelAssembler assembler;

    @Autowired
    ClienteInputDisassembler disassembler;

    @GetMapping
    public Collection<ClienteModel> obterLista() {
        log.info("API de Cliente - obterLista");

        return assembler.toCollection(clienteService.obterLista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteModel> obterCliente(@PathVariable Integer id) {
        try {
            log.info("API de Cliente - obterCliente");

            ClienteModel clienteModel = assembler.toModel(clienteService.obterCliente(id));

            return ResponseEntity.ok(clienteModel);
        } catch(ClienteNaoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ClienteModel incluir(@RequestBody ClienteInput input) {
        log.info("API de Cliente - incluir");

        Cliente cliente = disassembler.toDomainObject(input);

        cliente = clienteService.incluir(cliente);

        return assembler.toModel(cliente);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Integer id) {
        log.info("API de Cliente - excluir");

        clienteService.excluir(id);
    }
}
