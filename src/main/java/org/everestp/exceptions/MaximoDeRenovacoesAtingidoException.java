package org.everestp.exceptions;

public class MaximoDeRenovacoesAtingidoException extends RuntimeException {
    public MaximoDeRenovacoesAtingidoException() {
        super("Máximo de renovações atingido para o empréstimo.");
    }
}
