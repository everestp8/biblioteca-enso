package org.everestp.exceptions;

public class EmprestimosPendentesException extends RuntimeException {
    public EmprestimosPendentesException() {
        super("Ainda há empréstimos pendentes na conta.");
    }
}
