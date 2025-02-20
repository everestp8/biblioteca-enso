package org.everestp.exceptions;

public class ExemplarNaoEncontradoException extends RuntimeException {
    public ExemplarNaoEncontradoException() {
        super("Exemplar não encontrado.");
    }
}
