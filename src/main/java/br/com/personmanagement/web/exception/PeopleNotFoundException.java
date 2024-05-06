package br.com.personmanagement.web.exception;

public class PeopleNotFoundException extends ResourceNotFoundException {

    public PeopleNotFoundException(String message) {
        super(message);
    }
}
