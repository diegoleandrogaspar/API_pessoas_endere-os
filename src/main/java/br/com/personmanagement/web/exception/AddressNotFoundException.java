package br.com.personmanagement.web.exception;

public class AddressNotFoundException extends ResourceNotFoundException{

    public AddressNotFoundException(String message) {
        super(message);
    }
}
