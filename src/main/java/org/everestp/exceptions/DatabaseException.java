package org.everestp.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseException(Throwable cause) {
        super("Erro ao acessar banco de dados.", cause);
    }

    public DatabaseException(String message) {
        super(message);
    }
}
