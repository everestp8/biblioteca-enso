package org.everestp.exceptions;

public class ExemplarNaoEncontradoException extends RuntimeException {
    public ExemplarNaoEncontradoException() {
        super("Não foi possível encontrar um exemplar com esse ID físico.");
    }
}
