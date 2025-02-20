package org.everestp.exceptions;

public class ExemplarNaoDisponivelException extends RuntimeException {
    public ExemplarNaoDisponivelException() {
        super("Exemplar não disponível para empréstimo.");
    }
}
