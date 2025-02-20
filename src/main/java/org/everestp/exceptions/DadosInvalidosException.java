package org.everestp.exceptions;

public class DadosInvalidosException extends RuntimeException {
    public DadosInvalidosException(String message) {
        super(message);
    }

    public DadosInvalidosException() {
        super("Dados inválidos.");
    }
}
