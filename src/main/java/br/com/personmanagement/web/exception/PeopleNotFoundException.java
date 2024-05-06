package br.com.personmanagement.web.exception;

public class PersonNotFoundException extends ResourceNotFoundException {

    public PersonNotFoundException(String message) {
        super(message);
    }
}
