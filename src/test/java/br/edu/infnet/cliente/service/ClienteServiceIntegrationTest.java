package br.edu.infnet.cliente.service;

import br.edu.infnet.cliente.model.domain.Cliente;
import br.edu.infnet.cliente.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ClienteServiceIntegrationTest {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteRepository clienteRepository;

    private static MySQLContainer container = (MySQLContainer) new MySQLContainer("mysql:latest")
            .withReuse(true );

    @BeforeAll
    public static void setup() {
        container.start();
    }

    @DynamicPropertySource
    private static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

    @Test
    void incluir() {
        Cliente cliente = new Cliente();
        cliente.setNome("john doe");
        cliente.setEmail("johndoe@email.com");
        cliente.setCpf("99999999999");

        Cliente clienteSalvo = clienteService.incluir(cliente);

        assertThat(clienteSalvo).isNotNull();
    }

    @Test
    void obterLista() {
        Collection<Cliente> lista = clienteService.obterLista();

        assertTrue(lista != null);
    }

}