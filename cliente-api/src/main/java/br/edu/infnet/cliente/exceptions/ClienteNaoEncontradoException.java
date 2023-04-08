package br.edu.infnet.cliente.exceptions;

import org.slf4j.LoggerFactory;

public class ClienteNaoEncontradoException extends RuntimeException {

    public ClienteNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
