package br.edu.infnet.cliente.service;

import br.edu.infnet.cliente.exceptions.ClienteNaoEncontradoException;
import br.edu.infnet.cliente.exceptions.CpfInvalidoException;
import br.edu.infnet.cliente.model.domain.Cliente;
import br.edu.infnet.cliente.repository.ClienteRepository;
import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    ClienteRepository repository = mock(ClienteRepository.class);

    @Test
    void incluir_comMockito() {
        Cliente cliente = new Cliente();
        cliente.setNome("john doe");
        cliente.setEmail("johndoe@email.com");
        cliente.setCpf("99999999999");

        when(repository.save(cliente)).thenReturn(new Cliente());

        Cliente clienteSalvo = clienteService.incluir(cliente);

        assertThat(clienteSalvo).isNotNull();
    }

    @Test
    void obterLista() {
        Collection<Cliente> lista = clienteService.obterLista();

        assertTrue(lista != null);
    }

    @Test
    void incluir_validarCpfNulo() {
        Cliente cliente = new Cliente();
        cliente.setNome("john doe");
        cliente.setEmail("johndoe@email.com");

        Exception exception = assertThrows(CpfInvalidoException.class, () -> clienteService.incluir(cliente));

        assertTrue(exception.getMessage().contains("Não é possível aceitar CPF nulo"));
    }

    @Test
    void incluir_validarCpfEmVazio() {
        Cliente cliente = new Cliente();
        cliente.setNome("john doe");
        cliente.setEmail("johndoe@email.com");
        cliente.setCpf(" ");

        Exception exception = assertThrows(CpfInvalidoException.class, () -> clienteService.incluir(cliente));

        assertTrue(exception.getMessage().contains("Não é possível aceitar CPF vazio"));
    }

    @Test
    void excluir_idNaoEncontrado() {
        Exception exception = assertThrows(ClienteNaoEncontradoException.class,
                () -> clienteService.excluir(123));

        assertThat(exception.getMessage()).contains("cliente com id 123 nao encontrado");
    }
}