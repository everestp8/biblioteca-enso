package org.everestp.exceptions;

public class ExemplaresEmUsoException extends RuntimeException {
    public ExemplaresEmUsoException() {
        super("Não foi possível deletar os exemplares pois todos não foram devolvidos.");
    }
}
