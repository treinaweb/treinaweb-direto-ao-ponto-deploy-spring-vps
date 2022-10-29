package br.com.treinaweb.twtodos.core.exceptions;

public class TodoNotFoundException extends ModelNotFoundException {

    public TodoNotFoundException() {
        super("Todo not found");
    }

    public TodoNotFoundException(String message) {
        super(message);
    }

}
