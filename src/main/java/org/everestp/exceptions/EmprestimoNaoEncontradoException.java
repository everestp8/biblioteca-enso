package org.everestp.exceptions;

public class EmprestimoNaoEncontradoException extends RuntimeException {
    public EmprestimoNaoEncontradoException() {
        super("Não foi possível encontrar um empréstimo ativo para esta ação.");
    }
}
