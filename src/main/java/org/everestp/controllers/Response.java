package org.everestp.controllers;

public class Response<T> {
    private final T data;
    private final Exception error;

    public Response(T data, Exception error) {
        this.data = data;
        this.error = error;
    }

    public static <T> Response<T> sucesso(T data) {
        return new Response<>(data, null);
    }

    public static <T> Response<T> falha(Exception error) {
        return new Response<>(null, error);
    }

    public static Response<Void> sucesso() {
        return new Response<>(null, null);
    }

    public boolean isError() {
        return this.error != null;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        if (error == null)
            return null;

        String message = error.getMessage();
        if (error.getCause() != null)
            message += " (causado por: " + error.getCause().getMessage() + ")";

        return message;
    }
}
